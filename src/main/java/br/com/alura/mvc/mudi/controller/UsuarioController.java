package br.com.alura.mvc.mudi.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("pedido")
	public ModelAndView home() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(username);
		pedidos.sort(Comparator.comparing(Pedido::getStatus));
		
		ModelAndView mv = new ModelAndView("usuario/home");
		mv.addObject("pedidos", pedidos);
		return mv;
	}

	@GetMapping("pedido/{status}")
	public ModelAndView porStatus(@PathVariable("status") String status) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		List<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(StatusPedido.valueOf(status.toUpperCase()),
				username);
		ModelAndView mv = new ModelAndView("usuario/home");
		mv.addObject("pedidos", pedidos);
		mv.addObject("status", status);
		return mv;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView onError() {
		ModelAndView mv = new ModelAndView("redirect:/usuario/home");
		return mv;
	}

}
