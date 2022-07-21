package com.example.restaurant.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurant.model.Adm;
import com.example.restaurant.model.Cliente;
import com.example.restaurant.model.FormaDePagamento;
import com.example.restaurant.model.Pedido;
import com.example.restaurant.model.Prato;
import com.example.restaurant.repository.AdmRepository;
import com.example.restaurant.repository.ClienteRepository;
import com.example.restaurant.repository.FormaDePagamentoRepository;
import com.example.restaurant.repository.PedidoRepository;
import com.example.restaurant.repository.PratoRepository;

@Controller
public class RestaurantController {

    // Start
    @RequestMapping("/")
    public String inicial(Model m) {
        return "index";
    }

    // Usuario
    @RequestMapping("/usuario/cadastro")
    public String cadastroUsuario(Model m) {
        return "/usuario/cadastro";
    }

    @RequestMapping("/usuario/cadastrar")
    public String cadastrarUsuario(Model m, Cliente c) throws ClassNotFoundException, SQLException {
        ClienteRepository.getCurrentInstance().create(c);

        return "index";
    }

    @RequestMapping("/usuario/login")
    public String loginUsuario(Model m) {
        return "/usuario/login";
    }

    @RequestMapping("/usuario/logar")
    public String logarUsuario(Model m, HttpServletRequest request) {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Cliente client = ClienteRepository.getCurrentInstance().read(email, "email");

        if ((!(client == null)) && client.getEmail().equals(email) && client.getSenha().equals(senha)) {
            this.addPratosAndCliente(m, client.getID());
            return "/usuario/inicialRestaurante";
        } else {
            m.addAttribute("msgErro", "Usuário ou senha incorreto");
        }

        return "/usuario/login";

    }

    @RequestMapping("/usuario/fazerPedido/{PratoID}/{ClienteID}")
    public String fazerPedido(Model m, @PathVariable("PratoID") int PratoID, @PathVariable("ClienteID") int ClienteID) {
        Prato solicitado = PratoRepository.getCurrentInstance().read(PratoID);
        m.addAttribute("prato", solicitado);
        m.addAttribute("fp", FormaDePagamentoRepository.getCurrentInstance().readAll());
        m.addAttribute("clienteID", ClienteID);

        return "/usuario/fazerPedido";
    }

    @RequestMapping("/usuario/confirmaPedido/{PratoID}/{ClienteID}")
    public String confirmaPedido(Model m, HttpServletRequest request, @PathVariable("PratoID") int PratoID,
            @PathVariable("ClienteID") int ClienteID) throws ClassNotFoundException, SQLException {
        Pedido pedido = new Pedido();
        pedido.setDonoDoPedido(ClienteRepository.getCurrentInstance().read(ClienteID));
        pedido.setDonoDoPedidoID(ClienteID);
        pedido.setObservacao(request.getParameter("observacao"));
        pedido.setPrato(PratoRepository.getCurrentInstance().read(PratoID));
        pedido.setFormaDePagamento(FormaDePagamentoRepository.getCurrentInstance()
                .read(Integer.parseInt(request.getParameter("formaDePagamento"))));

        PedidoRepository.getCurrentInstance().create(pedido);

        m.addAttribute("msg", "Pedido solicitado com sucesso.");
        this.addPratosAndCliente(m, ClienteID);
        return "/usuario/inicialRestaurante";

    }

    @RequestMapping("/usuario/pedidos/{ClienteID}")
    public String listarPedidosUsuario(Model m, @PathVariable("ClienteID") String clienteID) {
        m.addAttribute("pedidos", PedidoRepository.getCurrentInstance().readAll(clienteID, "donoDoPedidoID"));

        Cliente cl = ClienteRepository.getCurrentInstance().read(Integer.parseInt(clienteID));
        m.addAttribute("cliente", cl.getNome());
        m.addAttribute("clienteID", cl.getID());

        return "/usuario/pedidosSolicitados";
    }

    @RequestMapping("/usuario/inicialUsuario/{ClienteID}")
    public String inicialUsuario(Model m, @PathVariable("ClienteID") int ClienteID) {
        this.addPratosAndCliente(m, ClienteID);
        return "/usuario/inicialRestaurante";
    }

    // Adm

    @RequestMapping("/adm/login")
    public String loginAdm(Model m) {
        return "/adm/login";
    }

    @RequestMapping("/adm/logar")
    public String logarAdm(Model m, HttpServletRequest request) {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Adm adm = AdmRepository.getCurrentInstance().read(email, "email");
        if (!(adm == null) && adm.getEmail().equals(email) && adm.getSenha().equals(senha)) {
            return "/adm/inicialAdm";
        } else {
            m.addAttribute("msgErro", "Usuário ou senha incorreto");
        }

        return "/adm/login";
    }

    @RequestMapping("/adm/cadastroPrato")
    public String cadastroPrato() {
        return "/adm/cadastroPrato";
    }

    @RequestMapping("/adm/cadastrarPrato")
    public String cadastrarPrato(Model m, Prato p) throws ClassNotFoundException, SQLException {
        PratoRepository.getCurrentInstance().create(p);
        m.addAttribute("msg", "Prato cadastrado com sucesso");
        return "/adm/inicialAdm";
    }

    @RequestMapping("/adm/cadastroFP")
    public String cadastroFP() {
        return "adm/cadastroFP";
    }

    @RequestMapping("/adm/cadastrarFP")
    public String cadastrarFP(Model m, HttpServletRequest request, FormaDePagamento fp)
            throws ClassNotFoundException, SQLException {
        FormaDePagamentoRepository.getCurrentInstance().create(fp);
        m.addAttribute("msg", "Forma de pagamento cadastrada com sucesso");

        return "/adm/inicialAdm";
    }

    @RequestMapping("/adm/clientes")
    public String visualizarClientes(Model m) {
        m.addAttribute("clientes", ClienteRepository.getCurrentInstance().readAll());
        return "/adm/clientes";
    }

    @RequestMapping("/adm/inicialAdm")
    public String inicialAdm() {
        return "/adm/inicialAdm";
    }

    @RequestMapping("/adm/visualizarPedidos")
    public String visualizarPedidosAdm(Model m) {
        m.addAttribute("pedidos", PedidoRepository.getCurrentInstance().readAll());
        return "/adm/pedidos";
    }

    //
    public void addPratosAndCliente(Model m, int ClienteID) {
        m.addAttribute("pratos", PratoRepository.getCurrentInstance().readAll());
        Cliente cl = ClienteRepository.getCurrentInstance().read(ClienteID);
        m.addAttribute("cliente", cl.getNome());
        m.addAttribute("clienteID", cl.getID());
    }

}
