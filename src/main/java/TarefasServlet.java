import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tarefas")
public class TarefasServlet extends HttpServlet {
    TarefasDAO tarefasDao = new TarefasDAO();
    final TarefaValidator validator = new TarefaValidator();

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<Tarefa> tarefasNaoConcluidas = tarefasDao.listarTarefas();
        request.setAttribute("listaDeTarefas", tarefasNaoConcluidas);
        request.getRequestDispatcher("templates/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metodo = request.getParameter("_method");
        if(metodo != null) {
            switch (metodo){
                case "PUT"->doPut(request, response);
                case "DELETE"->doDelete(request, response);
            }
            return;
        }
        String titulo = request.getParameter("titulo");
        Tarefa tarefa = new Tarefa(titulo, false);
        validator.validarInsercao(tarefa);
        tarefasDao.inserir(tarefa);
        redirecionarTarefa(response);
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao(titulo);
        validator.validarInsercao(tarefa);
        tarefasDao.atualizarDescricao(tarefa);
        redirecionarTarefa(response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        tarefasDao.deletarTarefa(id);
        redirecionarTarefa(response);
    }

    protected void redirecionarTarefa(HttpServletResponse response) throws IOException {
        response.sendRedirect("/tarefas");
    }
}
