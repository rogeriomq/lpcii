package br.edu.unirg.projeto.conexao.dao;

import br.edu.unirg.projeto.bean.Registro;
import br.edu.unirg.projeto.conexao.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroDAO {

    ConexaoDB conexao;

    public List<Registro> findAllRegistros() {
        conexao = new ConexaoDB();
        List<Registro> lista = new ArrayList<>();
        String sql = "SELECT * FROM REGISTRO R ORDER BY R.NOME";
        try {
            Statement st = conexao.connect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Registro reg = new Registro();
                reg.setId(rs.getString("ID"));
                reg.setNome(rs.getString("NOME"));
                reg.setSobrenome(rs.getString("SOBRENOME"));
                reg.setAniversario(rs.getDate("ANIVERSARIO"));
                reg.setApelido(rs.getString("APELIDO"));
                reg.setEndereco(rs.getString("ENDERECO"));
                lista.add(reg);
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public String saveOrUpdate(Registro p) { // COM ON DUPLICATE KEY, APENAS NO MYSQL E NO MARIADB
        conexao = new ConexaoDB();
        String camposNaoChave = "NOME = ?, SOBRENOME = ?, APELIDO = ?, ANIVERSARIO = ?, ENDERECO = ?";

        String sqlInsertOrUpdate = "INSERT INTO REGISTRO SET ID = ?," + camposNaoChave + " ON DUPLICATE KEY UPDATE " + camposNaoChave;
        //tanto no insert quanto no update, vai ser retornado o ID, caso o ID seja nulo, é pq o SQL nao foi realmente executado.
        String pkReturn = null;
        try {
            PreparedStatement ps = conexao.connect()
                    .prepareStatement(sqlInsertOrUpdate,
                            Statement.RETURN_GENERATED_KEYS);
            System.out.println("ID REGISTRO = " + p.getId());
            //insert Caso o ID seja null, então passo um randomUUID() para o novo contato. 
            //Operador ternário aqui é uma ótima alternativa.
            ps.setString(1, p.getId() == null ? UUID.randomUUID().toString() : p.getId());//Id somente no insert
            ps.setString(2, p.getNome());
            ps.setString(3, p.getSobrenome());
            ps.setString(4, p.getApelido());
            ps.setDate(5, (p.getAniversario() != null ? new java.sql.Date(p.getAniversario().getTime()) : null));
            ps.setString(6, p.getEndereco());
            //update
            ps.setString(7, p.getNome());
            ps.setString(8, p.getSobrenome());
            ps.setString(9, p.getApelido());
            ps.setDate(10, (p.getAniversario() != null ? new java.sql.Date(p.getAniversario().getTime()) : null));
            ps.setString(11, p.getEndereco());

            System.out.println("SQL= " + ps.toString());
            int result = ps.executeUpdate();
            System.out.println("RESULT EXEC SQL= " + result);
            pkReturn = p.getId();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return pkReturn;
    }

    public String saveOrUpdateGenerico(Registro p) {
        conexao = new ConexaoDB();
        String camposNaoChave = "NOME = ?, SOBRENOME = ?, APELIDO = ?, ANIVERSARIO = ?, ENDERECO = ?";
        String sqlInsert = "INSERT INTO REGISTRO SET ID = ?," + camposNaoChave;
        String sqlUpdate = "UPDATE REGISTRO SET " + camposNaoChave + " WHERE ID=?";
        //tanto no insert quanto no update, vai ser retornado o ID, caso o ID seja nulo, é pq o SQL nao foi realmente executado.
        String pkReturn = null;
        try {
            ResultSet rs = conexao.connect().createStatement().executeQuery("SELECT COUNT(*) FROM REGISTRO R WHERE R.ID = " + p.getId());
            PreparedStatement ps;
            if (rs.getFetchSize() == 0) { //INSERT
                ps = conexao.connect()
                        .prepareStatement(sqlInsert,
                                Statement.RETURN_GENERATED_KEYS);
                System.out.println("ID REGISTRO = " + p.getId());
                //insert Caso o ID seja null, então passo um randomUUID() para o novo contato. 
                //Operador ternário aqui é uma ótima alternativa.
                ps.setString(1, UUID.randomUUID().toString());//Id somente no insert
                ps.setString(2, p.getNome());
                ps.setString(3, p.getSobrenome());
                ps.setString(4, p.getApelido());
                ps.setDate(5, (p.getAniversario() != null ? new java.sql.Date(p.getAniversario().getTime()) : null));
                ps.setString(6, p.getEndereco());
            } else { // UPDATE
                ps = conexao.connect()
                        .prepareStatement(sqlUpdate,
                                Statement.RETURN_GENERATED_KEYS);
                System.out.println("ID REGISTRO = " + p.getId());
                //insert Caso o ID seja null, então passo um randomUUID() para o novo contato. 
                //Operador ternário aqui é uma ótima alternativa.
                ps.setString(1, p.getNome());
                ps.setString(2, p.getSobrenome());
                ps.setString(3, p.getApelido());
                ps.setDate(4, (p.getAniversario() != null ? new java.sql.Date(p.getAniversario().getTime()) : null));
                ps.setString(5, p.getEndereco());
                ps.setString(6, p.getId());//Id somente no insert
            }

            System.out.println("SQL= " + ps.toString());
            int result = ps.executeUpdate();
            System.out.println("RESULT EXEC SQL= " + result);
            pkReturn = p.getId();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return pkReturn;
    }

    public int delete(String id) {
        int result = 0;
        conexao = new ConexaoDB();
        String sql = "DELETE FROM REGISTRO R WHERE R.ID = ?";
        try {
            PreparedStatement ps = conexao.connect()
                    .prepareStatement(sql);
            ps.setString(1, id);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Registro findById(String id) {
        conexao = new ConexaoDB();
        Registro reg = new Registro();
        String sql = "SELECT * FROM REGISTRO R WHERE R.ID = ?";
        try {
            PreparedStatement ps = conexao.connect().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                reg.setId(rs.getString("ID"));
                reg.setNome(rs.getString("NOME"));
                reg.setSobrenome(rs.getString("SOBRENOME"));
                reg.setAniversario(rs.getDate("ANIVERSARIO"));
                reg.setApelido(rs.getString("APELIDO"));
                reg.setEndereco(rs.getString("ENDERECO"));
            } else {
                reg = null;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return reg;
    }
}
