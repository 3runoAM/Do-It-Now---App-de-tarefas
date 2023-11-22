import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tarefas")
public class TarefasServlet extends HttpServlet {
    TarefasDAO tarefas = new TarefasDAO();
    final TarefaValidator validator = new TarefaValidator();
    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<Tarefa> tarefasNaoConcluidas = tarefas.listarTarefas();
        request.setAttribute("listaDeTarefas", tarefasNaoConcluidas);
        request.getRequestDispatcher("templates/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String titulo = request.getParameter("titulo");
       Tarefa tarefa = new Tarefa(titulo, true);
       validator.validarInsercao(tarefa);
       tarefas.inserir(tarefa);
       response.sendRedirect("/tarefas");
    }
}

