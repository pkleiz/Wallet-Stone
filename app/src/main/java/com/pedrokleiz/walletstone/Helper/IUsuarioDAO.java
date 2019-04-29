package com.pedrokleiz.walletstone.Helper;

import com.pedrokleiz.walletstone.Model.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    public boolean salvar (Usuario usuario);
    public boolean atualizar (Usuario usuario);
    public boolean deletar (Usuario usuario);
    public List<Usuario> listar (String usuario);
}
