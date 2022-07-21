package com.example.restaurant.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.restaurant.model.Pedido;

public class PedidoRepository {

    private static int ID = 1;
    private static PedidoRepository myself = null;

    private PedidoRepository() {
    }

    public static PedidoRepository getCurrentInstance() {
        if (myself == null) {
            myself = new PedidoRepository();
        }
        return myself;
    }

    /**
     * @param cl
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void create(Pedido cl) throws ClassNotFoundException, SQLException {
        String sql = "insert into Pedidos(donoDoPedido, donoDoPedidoID, prato, formaDePagamento, observacao,ID) values (?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getDonoDoPedido().getNome());
            pstm.setInt(2, cl.getDonoDoPedidoID());
            pstm.setString(3, cl.getPrato().getNome());
            pstm.setString(4, cl.getFormaDePagamento().getNome());
            pstm.setString(5, cl.getObservacao());
            pstm.setInt(6, ID++);

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void update(Pedido cl) throws ClassNotFoundException, SQLException {
        String sql = "update Pedidos set donoDoPedido = ?, donoDoPedidoID = ?, prato=?, formaDePagamento=?, observacao = ? where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setString(1, cl.getDonoDoPedido().getNome());
            pstm.setInt(2, cl.getDonoDoPedidoID());
            pstm.setString(3, cl.getPrato().getNome());
            pstm.setString(4, cl.getFormaDePagamento().getNome());
            pstm.setString(5, cl.getObservacao());
            pstm.setInt(6, cl.getID());

            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Pedido read(int ID) {
        String sql = "select * from Pedidos where ID=?";

        try {

            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                Pedido cl = new Pedido();

                cl.setDonoDoPedido(
                        ClienteRepository.getCurrentInstance().read(result.getString("donoDoPedido"), "nome"));
                cl.setDonoDoPedidoID(Integer.parseInt(result.getString("donoDoPedidoID")));
                cl.setPrato(PratoRepository.getCurrentInstance().read(result.getString("prato")));
                cl.setFormaDePagamento(FormaDePagamentoRepository.getCurrentInstance()
                        .read(result.getString("formaDePagamento"), "nome"));
                cl.setObservacao(result.getString("observacao"));
                cl.setID(result.getInt("ID"));
                return cl;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void delete(int ID) {
        String sql = "delete from Pedidos where ID = ?";

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            pstm.setInt(1, ID);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pedido> readAll() {
        String sql = "select * from Pedidos";
        List<Pedido> Pedidos = new ArrayList<Pedido>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Pedido cl = new Pedido();
                cl.setDonoDoPedido(
                        ClienteRepository.getCurrentInstance().read(result.getString("donoDoPedido"), "nome"));
                cl.setDonoDoPedidoID(Integer.parseInt(result.getString("donoDoPedidoID")));
                cl.setPrato(PratoRepository.getCurrentInstance().read(result.getString("prato")));
                cl.setFormaDePagamento(FormaDePagamentoRepository.getCurrentInstance()
                        .read(result.getString("formaDePagamento"), "nome"));
                cl.setObservacao(result.getString("observacao"));
                cl.setID(result.getInt("ID"));
                Pedidos.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Pedidos;
    }

    public List<Pedido> readAll(String search, String field) {
        String sql = "select * from Pedidos where ";
        if (field == "donoDoPedido" || field == "donoDoPedidoID" || field == "prato" || field == "formaDePagamento" || field == "observacao") {
            sql += field + " = ?";
        } else
            return null;
        List<Pedido> Pedidos = new ArrayList<Pedido>();

        try {
            PreparedStatement pstm = com.example.restaurant.dao.ConnectionDB.getCurrentConnection()
                    .prepareStatement(sql);

            if(field == "donoDoPedidoID") {
                pstm.setInt(1,Integer.parseInt(search));
            } else {
                pstm.setString(1, search);
            }

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Pedido cl = new Pedido();
                cl.setDonoDoPedido(
                        ClienteRepository.getCurrentInstance().read(result.getString("donoDoPedido"), "nome"));
                cl.setDonoDoPedidoID(Integer.parseInt(result.getString("donoDoPedidoID")));
                cl.setPrato(PratoRepository.getCurrentInstance().read(result.getString("prato")));
                cl.setFormaDePagamento(FormaDePagamentoRepository.getCurrentInstance()
                        .read(result.getString("formaDePagamento"), "nome"));
                cl.setObservacao(result.getString("observacao"));
                cl.setID(result.getInt("ID"));
                Pedidos.add(cl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Pedidos;
    }

}
