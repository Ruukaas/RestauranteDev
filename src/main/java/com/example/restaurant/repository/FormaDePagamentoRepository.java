package com.example.restaurant.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.restaurant.model.FormaDePagamento;

public class FormaDePagamentoRepository {

    private static int ID = 1;
    private static FormaDePagamentoRepository myself = null;

    private FormaDePagamentoRepository() {
    }

    public static FormaDePagamentoRepository getCurrentInstance() {
        if(myself == null) {
            myself = new FormaDePagamentoRepository();
        }
        return myself;
    }

    /**
     * @param cl
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void create(FormaDePagamento cl) throws ClassNotFoundException, SQLException {
        String sql = "insert into FormaDePagamentos(nome, ID) values (?,?)";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getNome());
            pstm.setInt(2, ID++);

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void update(FormaDePagamento cl) throws ClassNotFoundException, SQLException {
        String sql = "update FormaDePagamentos set nome = ? where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);
            pstm.setString(1, cl.getNome());
            pstm.setInt(2, cl.getID());

            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public FormaDePagamento read(int ID) {
        String sql = "select * from FormaDePagamentos where ID=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                FormaDePagamento cl = new FormaDePagamento();
                cl.setNome(result.getString("nome"));
                cl.setID(result.getInt("ID"));
                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public FormaDePagamento read(String search, String field) {
        String sql = "select * from FormaDePagamentos where ";
        if(field == "nome") {
            sql+= field + " = ?";
        } else return null; 

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, search);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                FormaDePagamento cl = new FormaDePagamento();
                cl.setNome(result.getString("nome"));
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
        String sql = "delete from FormaDePagamentos where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<FormaDePagamento> readAll() {
        String sql = "select * from FormaDePagamentos";
        List<FormaDePagamento> FormaDePagamentos = new ArrayList<FormaDePagamento>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                FormaDePagamento cl = new FormaDePagamento();
                cl.setNome(result.getString("nome"));
                cl.setID(result.getInt("ID"));
                FormaDePagamentos.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormaDePagamentoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FormaDePagamentos;
    }

}
