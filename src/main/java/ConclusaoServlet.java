import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/conclusao")
public class ConclusaoServlet extends HttpServlet {
    private final TarefasDAO tarefasDao = new TarefasDAO();

    /**
     * Lida com solicitações do tipo PUT para atualizar o status de uma tarefa.
     *
     * @param request  Objeto HttpServletRequest contendo a requisição do cliente.
     * @param response Objeto HttpServletResponse para enviar a resposta ao cliente.
     * @throws ServletException Se ocorrer uma exceção relacionada ao servlet.
     * @throws IOException      Se ocorrer uma exceção de entrada/saída.
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tarefa tarefa = tarefasDao.getTarefaPorId(id);
        tarefa.setConcluido(!tarefa.isConcluido());
        tarefasDao.atualizarStatus(tarefa);
    }
}
