package com.example.restaurant.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.restaurant.model.Cliente;

public class ClienteRepository {

    private static int ID = 1;
    private static ClienteRepository myself = null;

    private ClienteRepository() {

    }

    public static ClienteRepository getCurrentInstance() {
        if(myself == null) {
            myself = new ClienteRepository();
        }
        return myself;
    }
    
    /**
     * @param cl
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void create(Cliente cl) throws ClassNotFoundException, SQLException {
        String sql = "insert into clientes(nome, email, senha, telefone, ID) values (?,?,?,?,?)";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getNome());
            pstm.setString(2, cl.getEmail());
            pstm.setString(3, cl.getSenha());
            pstm.setInt(4, cl.getTelefone());
            pstm.setInt(5, ID++);

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void update(Cliente cl) throws ClassNotFoundException, SQLException {
        String sql = "update clientes set nome = ?, email=?, senha=?, telefone =? where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);
            pstm.setString(1, cl.getNome());
            pstm.setString(2, cl.getEmail());
            pstm.setString(3, cl.getSenha());
            pstm.setInt(4, cl.getTelefone());
            pstm.setInt(5, cl.getID());

            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Cliente read(int ID) {
        String sql = "select * from clientes where ID=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Cliente cl = new Cliente();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
                cl.setTelefone(result.getInt("telefone"));
                cl.setID(result.getInt("ID"));

                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Cliente read(String search, String field) {
        String sql = "select * from clientes where ";
        if(field == "nome" || field == "email" || field == "telefone") {
            sql+= field + " = ?";
        } else return null; 

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, search);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Cliente cl = new Cliente();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
                cl.setTelefone(result.getInt("telefone"));
                cl.setID(result.getInt("ID"));

                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }



    public void delete(int ID) {
        String sql = "delete from clientes where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> readAll() {
        String sql = "select * from clientes";
        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Cliente cl = new Cliente();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
                cl.setTelefone(result.getInt("telefone"));
                cl.setID(result.getInt("ID"));
                clientes.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }

}
