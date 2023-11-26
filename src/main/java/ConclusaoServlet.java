import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/conclusao")
public class ConclusaoServlet extends HttpServlet {
    TarefasDAO tarefasDao = new TarefasDAO();
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarefa tarefa = tarefasDao.getTarefaPorId(id);
        tarefa.setConcluido(!tarefa.isConcluido());
        tarefasDao.atualizarStatus(tarefa);
    }
}
