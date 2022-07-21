package com.example.restaurant.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.restaurant.model.Adm;

public class AdmRepository {

    private static int ID = 1;
    private static AdmRepository myself = null;

    private AdmRepository() {
    }

    public static AdmRepository getCurrentInstance() {
        if(myself == null) {
            myself = new AdmRepository();
        }
        return myself;
    }

    /**
     * @param cl
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void create(Adm cl) throws ClassNotFoundException, SQLException {
        String sql = "insert into Adms(nome, email, senha, ID) values (?,?,?,?)";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getNome());
            pstm.setString(2, cl.getEmail());
            pstm.setString(3, cl.getSenha());
            pstm.setInt(4, ID++);

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
            ID--;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void update(Adm cl) throws ClassNotFoundException, SQLException {
        String sql = "update Adms set nome = ?, email=?, senha=? where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);
            pstm.setString(1, cl.getNome());
            pstm.setString(2, cl.getEmail());
            pstm.setString(3, cl.getSenha());
            pstm.setInt(4, cl.getID());

            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Adm read(int ID) {
        String sql = "select * from Adms where ID=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Adm cl = new Adm();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
                cl.setID(result.getInt("ID"));
                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Adm read(String search, String field) {
        String sql = "select * from Adms where ";
        if(field == "nome" || field == "email") {
            sql+= field + " = ?";
        } else return null; 

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, search);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Adm cl = new Adm();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
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
        String sql = "delete from Adms where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Adm> readAll() {
        String sql = "select * from Adms";
        List<Adm> Adms = new ArrayList<Adm>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Adm cl = new Adm();
                cl.setNome(result.getString("nome"));
                cl.setEmail(result.getString("email"));
                cl.setSenha(result.getString("senha"));
                cl.setID(result.getInt("ID"));
                Adms.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdmRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Adms;
    }

}
