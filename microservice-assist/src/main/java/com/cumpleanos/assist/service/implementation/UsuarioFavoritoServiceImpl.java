package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.inmutables.FavoriteRequest;
import com.cumpleanos.assist.persistence.repository.ProgramaWRepository;
import com.cumpleanos.assist.persistence.repository.UsuarioFavoritoRepository;
import com.cumpleanos.assist.persistence.transformers.MenuItemTransformer;
import com.cumpleanos.assist.persistence.transformers.MenuTransformer;
import com.cumpleanos.assist.service.exception.FavoriteNotFoundException;
import com.cumpleanos.assist.service.exception.ProgramaNotFoundException;
import com.cumpleanos.assist.service.exception.UserNotFoundException;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import com.cumpleanos.core.models.entities.ProgramaW;
import com.cumpleanos.core.models.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioFavoritoServiceImpl extends GenericServiceImpl<UsuarioFavoritos, Long> implements IUsuarioFavoritoService {

    private final UsuarioFavoritoRepository repository;
    private final ProgramaWRepository programaRepository;
    private final ClientServiceImpl modelsClient;

    private final IAccesoRolService accesoRolService;

    @Override
    public CrudRepository<UsuarioFavoritos, Long> getRepository() {
        return repository;
    }

    @Override
    public Set<UsuarioFavoritos> getFavoritosByUserAndEmpresa(Long idUsuario, Long empresa) {
        Set<MenuTransformer> menus = accesoRolService.obtenerMenusYSubmenus(idUsuario, empresa);
        Set<String> menuPaths = obtenerTodosLosRouterLinks(menus);

        // Filtrar los favoritos basándote en los `path` de los menús
        Set<UsuarioFavoritos> favoritos = repository.findByUsuario_IdAndEmpresa(idUsuario, empresa);
        return favoritos.stream()
                .filter(favorito -> menuPaths.contains(favorito.getPrograma().getPath()))
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioFavoritos saveFavorito(FavoriteRequest request) {

        Usuario usuario = modelsClient.getUsuarioByCodigo(request.idUsuario());
        if (usuario == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        ProgramaW programa = getPrograma(request.path());
        return super.save(UsuarioFavoritos.builder()
                .usuario(usuario)
                .programa(programa)
                .empresa(request.empresa())
                .build());
    }

    @Override
    public UsuarioFavoritos getFavoritoByUsuarioEmpresaPath(FavoriteRequest request) {
        ProgramaW programa = getPrograma(request.path());
        return repository.findByUsuario_IdAndEmpresaAndPrograma(request.idUsuario(), request.empresa(), programa).orElse(null);
    }

    @Override
    public void deleteFavoritoByUsuarioEmpresaPath(FavoriteRequest request) {
        ProgramaW programa = getPrograma(request.path());
        UsuarioFavoritos favorito = repository.findByUsuario_IdAndEmpresaAndPrograma(request.idUsuario(), request.empresa(), programa).orElseThrow(() -> new FavoriteNotFoundException("Favorito no encontrado"));
        repository.delete(favorito);
    }

    private ProgramaW getPrograma(String path) {
        return programaRepository.findByPath(path)
                .orElseThrow(() -> new ProgramaNotFoundException("Programa no encontrado"));
    }

    private Set<String> obtenerTodosLosRouterLinks(Set<MenuTransformer> menus) {
        Set<String> routerLinks = new HashSet<>();
        for (MenuTransformer menu : menus) {
            if (menu.getItems() != null) {
                for (MenuItemTransformer item : menu.getItems()) {
                    obtenerRouterLinksRecursivo(item, routerLinks);
                }
            }
        }
        return routerLinks;
    }


    private void obtenerRouterLinksRecursivo(MenuItemTransformer item, Set<String> routerLinks) {
        if (item.getRouterLink() != null) {
            routerLinks.addAll(item.getRouterLink());
        }
        if (item.getItems() != null) {
            for (MenuItemTransformer subItem : item.getItems()) {
                obtenerRouterLinksRecursivo(subItem, routerLinks);
            }
        }
    }


}
