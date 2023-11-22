import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefasDAO {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/toDoApp";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "2121";
    private static Connection conexao;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**/
    public List<Tarefa> listarTarefas(){
        List<Tarefa> tarefasPendentes = new ArrayList<>();
            String query = "SELECT * FROM tarefa";
            try (PreparedStatement statement = conexao.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String descricao = resultSet.getString("descricao");
                    boolean concluido = resultSet.getBoolean("concluido");
                    tarefasPendentes.add(new Tarefa(descricao, concluido));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return tarefasPendentes;
    }

    public void inserir(Tarefa tarefa){
        String query = "INSERT INTO tarefa (id, descricao, concluido) VALUES (nextval('tarefa_sequence'), ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, tarefa.getDescricao());
            statement.setBoolean(2, tarefa.isConcluido());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Tarefa tarefa){
        String query = "UPDATE tarefa (descricao) VALUES (?) WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, tarefa.getDescricao());
            statement.setInt(2, tarefa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}