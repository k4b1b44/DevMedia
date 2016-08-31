package devMedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class Conexao {

    public static void main(String[] args) {
        
        
      String url = "jdbc:derby://localhost:1527/dev-media";
      String usuario = "root";
      String senha = "root";
      
        try {
            Connection con = DriverManager.getConnection(url, usuario, senha);
            
            
            Statement comando = con.createStatement();
            
            String adicionar = "INSERT INTO ROOT.ALUNOS (IDALUNO, NOME, SOBRENOME, CODCIDADE) \n" +
            "VALUES (108, 'Damiao ', 'Queiroz', 1)";
            
            int reg = comando.executeUpdate(adicionar);
            
            
            JOptionPane.showMessageDialog(null, "Quantidade de Registros modificados"+reg);
            
            System.out.println("Conexão realizada com sucesso!"); 
        } catch (SQLException e) {
            System.out.println("Erro: "+e.getMessage());
        }
       
        
        
    }
    
}
