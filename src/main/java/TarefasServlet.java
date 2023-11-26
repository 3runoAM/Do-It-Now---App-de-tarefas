import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tarefas")
public class TarefasServlet extends HttpServlet {
    private final TarefasDAO tarefasDao = new TarefasDAO();
    private final TarefaValidator validator = new TarefaValidator();

    /**
     * Método que lida com solicitações HTTP GET para exibir a lista de tarefas.
     *
     * @param request  um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido GET não puder ser tratado
     * @throws IOException      se um erro de entrada ou saída for detectado quando o servlet manipula o pedido GET
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<Tarefa> tarefasNaoConcluidas = tarefasDao.listarTarefas();
        request.setAttribute("listaDeTarefas", tarefasNaoConcluidas);
        request.getRequestDispatcher("templates/index.jsp").forward(request, response);
    }

    /**
     * Método que lida com a solicitações HTTP POST para criar uma nova tarefa.
     *
     * @param request  O objeto HttpServletRequest contendo os parâmetros da solicitação.
     * @param response O objeto HttpServletResponse para enviar a resposta.
     * @throws ServletException Se houver um erro no servlet.
     * @throws IOException      Se houver um erro de entrada/saída.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metodo = request.getParameter("_method");
        // Verifica se o parâmetro "_method" está presente
        if(metodo != null) {
            // Realiza a chamada do método apropriado com base no valor de "_method"
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

    /**
     * Método que lida com solicitações HTTP PUT para atualizar uma tarefa existente.
     *
     * @param request  O objeto HttpServletRequest contendo os parâmetros da solicitação.
     * @param response O objeto HttpServletResponse para enviar a resposta.
     * @throws ServletException Se houver um erro no servlet.
     * @throws IOException      Se houver um erro de entrada/saída.
     */
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

    /**
     * Método que lida com solicitações HTTP DELETE para excluir uma tarefa existente.
     *
     * @param request  O objeto HttpServletRequest contendo os parâmetros da solicitação.
     * @param response O objeto HttpServletResponse para enviar a resposta.
     * @throws ServletException Se houver um erro no servlet.
     * @throws IOException      Se houver um erro de entrada/saída.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        tarefasDao.deletarTarefa(id);
        redirecionarTarefa(response);
    }

    /**
     * Redireciona a resposta para a página de tarefas.
     *
     * @param response O objeto HttpServletResponse para enviar a resposta.
     * @throws IOException Se houver um erro de entrada/saída ao redirecionar.
     */
    protected void redirecionarTarefa(HttpServletResponse response) throws IOException {
        response.sendRedirect("/tarefas");
    }
}
