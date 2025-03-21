package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.transformers.MenuItemTransformer;
import com.cumpleanos.assist.persistence.transformers.MenuTransformer;
import com.cumpleanos.assist.persistence.repository.AccesoRolRepository;
import com.cumpleanos.assist.persistence.repository.MenuWRepository;
import com.cumpleanos.assist.persistence.repository.RolMenuRepository;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import com.cumpleanos.core.models.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AccesoRolServiceImpl extends GenericServiceImpl<AccesoRol, Long> implements IAccesoRolService {

    private final AccesoRolRepository accesoRolRepository;
    private final RolMenuRepository rolMenuRepository;
    private final MenuWRepository menuWRepository;

    @Override
    public CrudRepository<AccesoRol, Long> getRepository() {
        return accesoRolRepository;
    }

    /**
     * Metodo para obtener los menús y submenus de un usuario en una empresa específica.
     *
     * @param usuario El ID del usuario
     * @param empresa El ID de la empresa
     * @return Un conjunto de menús y submenus que el usuario puede acceder.
     */
    @Override
    public Set<MenuTransformer> obtenerMenusYSubmenus(Long usuario, Long empresa) {
        // Obtener los accesos del usuario en la empresa
        List<AccesoRol> accesoRols = accesoRolRepository.findByUsuarioAndEmpresaOrderByOrden(usuario, empresa);
        if (accesoRols == null || accesoRols.isEmpty()) {
            return new HashSet<>();
        }

        // Obtener los roles a partir de los accesos
        Set<RolW> roles = accesoRols.stream()
                .map(AccesoRol::getRolW)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // Obtener los menús asociados a los roles
        List<RolMenu> rolMenus = rolMenuRepository.findByRolWIn(new ArrayList<>(roles));

        Set<MenuW> menus = rolMenus.stream()
                .map(RolMenu::getMenuW)
                .sorted(Comparator.comparing(MenuW::getOrden))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // Crear una lista de menús de DTOs, añadiendo los submenus correctamente
        Set<MenuTransformer> menuTransformers = new LinkedHashSet<>();
        for (MenuW menu : menus) {
            MenuTransformer menuTransformer = buildMenuDTO(menu);  // Llamar al metodo recursivo para construir el DTO
            menuTransformers.add(menuTransformer);
        }

        return menuTransformers;
    }

    @Override
    public Set<Sistema> getEmpresas(Long usuario) {
        Set<AccesoRol> accesos = accesoRolRepository.findByUsuario(usuario);
        if (accesos == null || accesos.isEmpty()) {
            return new HashSet<>();
        }
        Set<Sistema> sistemas = new HashSet<>();
        for (AccesoRol rol : accesos) {
            sistemas.add(rol.getSistema());
        }
        return sistemas;
    }

    /**
     * Metodo recursivo para construir el MenuTransformer a partir de MenuW.
     */
    private MenuTransformer buildMenuDTO(MenuW menuW) {
        List<MenuItemTransformer> children = new ArrayList<>();

        // Buscar los submenus (menús que reporta el actual)
        Set<MenuW> subMenus = findSubMenus(menuW.getId());  // Busca los submenus que reporta este menú

        for (MenuW subMenu : subMenus) {
            // Llamar recursivamente para construir el DTO de cada submenú
            MenuItemTransformer subMenuItem = buildMenuItemDTO(subMenu);
            children.add(subMenuItem);
        }

        return MenuTransformer.builder()
                .label(menuW.getNombre())
                .icon(menuW.getIcono())
                .items(children)
                .build();
    }

    /**
     * Metodo recursivo para construir el MenuItemTransformer de cada menú.
     */
    private MenuItemTransformer buildMenuItemDTO(MenuW menuW) {
        List<MenuItemTransformer> children = new ArrayList<>();

        // Si el menú tiene submenus, buscar recursivamente los children
        Set<MenuW> subMenus = findSubMenus(menuW.getId());
        for (MenuW childMenu : subMenus) {
            children.add(buildMenuItemDTO(childMenu));  // Llamar recursivamente
        }

        // Si tiene programa, asignar el path como routerLink
        String routerLink = null;
        if (menuW.getPrograma() != null) {
            routerLink = menuW.getPrograma().getPath();
        } else {
            routerLink = "";
        }

        return MenuItemTransformer.builder()
                .label(menuW.getNombre())
                .icon(menuW.getIcono())
                .routerLink(routerLink.isEmpty() ? Collections.emptyList() : Collections.singletonList(routerLink))
                .items(children)
                .build();
    }

    /**
     * Metodo para obtener los submenus que reporta un menú (busca los menús por "mnw_reporta").
     */
    private Set<MenuW> findSubMenus(Long menuId) {
        return menuWRepository.findByReportaAndInactivoFalseOrderByOrden(menuId);  // Buscar los menús reportados por el menú con el ID `menuId`
    }
}