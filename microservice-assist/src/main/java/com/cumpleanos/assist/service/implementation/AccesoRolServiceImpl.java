package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.dto.MenuDTO;
import com.cumpleanos.assist.persistence.dto.MenuItemDTO;
import com.cumpleanos.assist.persistence.repository.AccesoRolRepository;
import com.cumpleanos.assist.persistence.repository.MenuWRepository;
import com.cumpleanos.assist.persistence.repository.RolMenuRepository;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import com.cumpleanos.core.models.entities.AccesoRol;
import com.cumpleanos.core.models.entities.MenuW;
import com.cumpleanos.core.models.entities.RolMenu;
import com.cumpleanos.core.models.entities.RolW;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccesoRolServiceImpl extends GenericServiceImpl<AccesoRol, Long> implements IAccesoRolService {

    private final AccesoRolRepository accesoRolRepository;
    private final RolMenuRepository rolMenuRepository;
    private final MenuWRepository menuWRepository;

    @Override
    public CrudRepository<AccesoRol, Long> getRepository() {
        return accesoRolRepository;
    }

    /**
     * Metodo para obtener los menús y submenús de un usuario en una empresa específica.
     * @param usuario El ID del usuario
     * @param empresa El ID de la empresa
     * @return Un conjunto de menús y submenús que el usuario puede acceder.
     */
    @Override
    public Set<MenuDTO> obtenerMenusYSubmenus(Long usuario, Long empresa) {
        // Obtener los accesos del usuario en la empresa
        List<AccesoRol> accesoRols = accesoRolRepository.findByUsuarioAndEmpresa(usuario, empresa);
        if (accesoRols == null || accesoRols.isEmpty()) {
            return new HashSet<>();
        }

        // Obtener los roles a partir de los accesos
        Set<RolW> roles = accesoRols.stream()
                .map(AccesoRol::getRolW)
                .collect(Collectors.toSet());

        // Obtener los menús asociados a los roles
        List<RolMenu> rolMenus = rolMenuRepository.findByRolWIn(new ArrayList<>(roles));
        Set<MenuW> menus = rolMenus.stream()
                .map(RolMenu::getMenuW)
                .collect(Collectors.toSet());

        // Crear una lista de menús de DTOs, añadiendo los submenús correctamente
        Set<MenuDTO> menuDTOs = new HashSet<>();
        for (MenuW menu : menus) {
            MenuDTO menuDTO = buildMenuDTO(menu);  // Llamar al método recursivo para construir el DTO
            menuDTOs.add(menuDTO);
        }

        return menuDTOs;
    }

    /**
     * Metodo recursivo para construir el MenuDTO a partir de MenuW.
     */
    private MenuDTO buildMenuDTO(MenuW menuW) {
        List<MenuItemDTO> children = new ArrayList<>();

        // Buscar los submenús (menús que reporta el actual)
        Set<MenuW> subMenus = findSubMenus(menuW.getId());  // Busca los submenús que reporta este menú

        for (MenuW subMenu : subMenus) {
            // Llamar recursivamente para construir el DTO de cada submenú
            MenuItemDTO subMenuItem = buildMenuItemDTO(subMenu);
            children.add(subMenuItem);
        }

        return MenuDTO.builder()
                .label(menuW.getNombre())
                .icon(menuW.getIcono())
                .items(children)
                .build();
    }

    /**
     * Metodo recursivo para construir el MenuItemDTO de cada menú.
     */
    private MenuItemDTO buildMenuItemDTO(MenuW menuW) {
        List<MenuItemDTO> children = new ArrayList<>();

        // Si el menú tiene submenús, buscar recursivamente los children
        Set<MenuW> subMenus = findSubMenus(menuW.getId());
        for (MenuW childMenu : subMenus) {
            children.add(buildMenuItemDTO(childMenu));  // Llamar recursivamente
        }

        // Si tiene programa, asignar el path como routerLink
        String routerLink = null;
        if (menuW.getPrograma() != null) {
            routerLink = menuW.getPrograma().getPath();
        }

        return MenuItemDTO.builder()
                .label(menuW.getNombre())
                .icon(menuW.getIcono())
                .routerLink(routerLink)
                .items(children)
                .build();
    }

    /**
     * Metodo para obtener los submenús que reporta un menú (busca los menús por "mnw_reporta").
     */
    private Set<MenuW> findSubMenus(Long menuId) {
        return menuWRepository.findByReporta(menuId);  // Buscar los menús reportados por el menú con el ID `menuId`
    }
}