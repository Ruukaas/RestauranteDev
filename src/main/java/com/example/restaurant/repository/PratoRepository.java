package com.example.restaurant.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.restaurant.model.Prato;

public class PratoRepository {

    private static int ID = 1;
    private static PratoRepository myself = null;

    private PratoRepository() {
    }

    public static PratoRepository getCurrentInstance() {
        if(myself==null) {
            myself = new PratoRepository();
        }
        return myself;
    }

    /**
     * @param cl
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void create(Prato cl) throws ClassNotFoundException, SQLException {
        String sql = "insert into Pratos(nome, preco, descricao, ID) values (?,?,?,?)";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getNome());
            pstm.setFloat(2, cl.getPreco());
            pstm.setString(3, cl.getDescricao());
            pstm.setInt(4, ID++);

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
            ID--;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void update(Prato cl) throws ClassNotFoundException, SQLException {
        String sql = "update Pratos set nome = ?, preco=?, descricao=? where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);
            pstm.setString(1, cl.getNome());
            pstm.setFloat(2, cl.getPreco());
            pstm.setString(3, cl.getDescricao());
            pstm.setInt(4, cl.getID());

            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Prato read(int ID) {
        String sql = "select * from Pratos where ID=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Prato cl = new Prato();
                cl.setNome(result.getString("nome"));
                cl.setPreco(result.getFloat("preco"));
                cl.setDescricao(result.getString("descricao"));
                cl.setID(result.getInt("ID"));
                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Prato read(String nome) {
        String sql = "select * from Pratos where nome=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, nome);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Prato cl = new Prato();
                cl.setNome(result.getString("nome"));
                cl.setPreco(result.getFloat("preco"));
                cl.setDescricao(result.getString("descricao"));
                cl.setID(result.getInt("ID"));
                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void delete(int ID) {
        String sql = "delete from Pratos where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Prato> readAll() {
        String sql = "select * from Pratos";
        List<Prato> Pratos = new ArrayList<Prato>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Prato cl = new Prato();
                cl.setNome(result.getString("nome"));
                cl.setPreco(result.getFloat("preco"));
                cl.setDescricao(result.getString("descricao"));
                cl.setID(result.getInt("ID"));
                Pratos.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PratoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Pratos;
    }

}
