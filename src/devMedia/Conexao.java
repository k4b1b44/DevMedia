package devMedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Conexao {
    
    private static  Connection con = null;
    private static Statement stmtAluno = null;
    private static Statement stmtCidade = null;
    private static ResultSet rsAluno = null;
    private static ResultSet rsCidade = null;

    public static void main(String[] args) {
        
        
      String url = "jdbc:derby://localhost:1527/dev-media";
      String usuario = "root";
      String senha = "root";
      
      
      
        try {
            con = DriverManager.getConnection(url, usuario, senha);
            
            String pesquisa = "SELECT * FROM ALUNOS";
            
            stmtAluno = con.createStatement();
            
            rsAluno = stmtAluno.executeQuery(pesquisa);
            
            boolean ultimo = rsAluno.next();
            
            while(ultimo){
                System.out.println("Código:     "+rsAluno.getString("IDALUNO"));
                System.out.println("NOME:       "+rsAluno.getString("NOME")+" "+rsAluno.getString("SOBRENOME"));
                System.out.println("Cidade:     "+ getCidade(rsAluno.getInt(4)));
                
                
                ultimo = rsAluno.next();
            }
           
            
            System.out.println("Conexão realizada com sucesso!!!!"); 
            
        } catch (SQLException e) {
            System.out.println("Erro: "+e.getMessage());
        }
        finally{
          try {
              rsAluno.close();
              rsCidade.close();
              stmtAluno.close();
              stmtCidade.close();
              con.close();
              
          } catch (SQLException erro) {
              System.err.println("Não foi possível fecha as conexões: "+erro);
          }
        }
       
        
        
    }

    private static String getCidade(int codCidade) throws SQLException {
        
        try {
            stmtCidade = con.createStatement();
            rsCidade = stmtCidade.executeQuery("SELECT CIDADE FROM CIDADES WHERE CODCIDADE="+codCidade);
            rsCidade.next();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar na tabela cidade: "+ex);
        }
        return rsCidade.getString("CIDADE");
       
    }
    
}
