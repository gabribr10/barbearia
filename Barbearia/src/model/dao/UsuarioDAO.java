/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Servico;
import model.bean.Usuario;

/**
 *
 * @author Gabriel Missio
 */
public class UsuarioDAO {
        
    public boolean inserirUsuario(Usuario u) {
        String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?, ?, ?)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public List<Usuario> read() {
          
        List<Usuario>  resultados = new ArrayList<>();  
        String sql = "SELECT codigo, nome, login FROM Usuario";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Usuario u = new Usuario();
                //nome da tabela do banco de dados
                u.setCodigo(rs.getInt("codigo"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                resultados.add(u);
                
            }
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
        
        public String readLogin(Usuario us) {
          
        List<Usuario>  resultados = new ArrayList<>();  
        String sql = "SELECT senha FROM usuario WHERE codigo = ?";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, us.getCodigo());
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Usuario u = new Usuario();
                //nome da tabela do banco de dados
                u.setSenha(rs.getString("senha"));
                resultados.add(u);
                
            }
         //   Collections.sort(resultados);
            String a = resultados.get(0).getSenha();
            return a;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
         public String readCadas(String us) {
          
        List<Usuario>  resultados = new ArrayList<>();  
        String sql = "SELECT login FROM usuario WHERE login = ?";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, us);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Usuario u = new Usuario();
                //nome da tabela do banco de dados
                u.setSenha(rs.getString("login"));
                resultados.add(u);
                
            }
            String a;
            //Collections.sort(resultados);
            if(resultados.size() == 0){
                a = "";
            }
            else{
              a = resultados.get(0).getSenha();
            }
            return a;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
        public boolean excluirUsuario(Usuario u) {
        String sql = "DELETE FROM usuario WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, u.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }    
        
    public boolean updateUsuario(Usuario u) {
        String sql = "UPDATE usuario SET senha = ? WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, u.getSenha());
            ps.setInt(2, u.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}

