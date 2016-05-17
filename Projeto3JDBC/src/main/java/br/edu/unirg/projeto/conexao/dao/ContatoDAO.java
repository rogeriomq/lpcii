package br.edu.unirg.projeto.conexao.dao;

import br.edu.unirg.projeto.bean.Contato;
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

public class ContatoDAO {

    ConexaoDB conexao;

    public List<Contato> findAllContatos(String idRegistro) {
        conexao = new ConexaoDB();
        List<Contato> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONTATO R WHERE R.REGISTRO_ID = ? ORDER BY R.DESCRICAO";
        try {
            PreparedStatement ps = conexao.connect().prepareStatement(sql);
            ps.setString(1, idRegistro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato reg = new Contato();
                reg.setId(rs.getString("ID"));
                reg.setTipo(rs.getInt("TIPO"));
                reg.setDescricao(rs.getString("DESCRICAO"));
                reg.setPreferencial(rs.getBoolean("PREFERENCIAL"));
                reg.setRegistro_id(rs.getString("REGISTRO_ID"));
                lista.add(reg);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public String saveOrUpdate(Contato p) { // COM ON DUPLICATE KEY, APENAS NO MYSQL E NO MARIADB
        conexao = new ConexaoDB();
        String camposNaoChave = "TIPO = ?, DESCRICAO = ?, PREFERENCIAL = ?, REGISTRO_ID = ?";

        String sqlInsertOrUpdate = "INSERT INTO CONTATO SET ID = ?," + camposNaoChave + 
                " ON DUPLICATE KEY UPDATE " + camposNaoChave;
        //tanto no insert quanto no update, vai ser retornado o ID, caso o ID seja nulo, é pq o SQL nao foi realmente executado.
        String pkReturn = null;
        try {
            PreparedStatement ps = conexao.connect()
                    .prepareStatement(sqlInsertOrUpdate,
                            Statement.RETURN_GENERATED_KEYS);
            System.out.println("ID CONTATO = " + p.getId());
            //insert Caso o ID seja null, então passo um randomUUID() para o novo contato. 
            //Operador ternário aqui é uma ótima alternativa.
            ps.setString(1, p.getId() == null ? UUID.randomUUID().toString() : p.getId());//Id somente no insert
            ps.setInt(2, p.getTipo());
            ps.setString(3, p.getDescricao());
            ps.setBoolean(4, p.isPreferencial());
            ps.setString(5, p.getRegistro_id());
            //update
            ps.setInt(6, p.getTipo());
            ps.setString(7, p.getDescricao());
            ps.setBoolean(8, p.isPreferencial());
            ps.setString(9, p.getRegistro_id());
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

    public int delete(String id) {
        int result = 0;
        conexao = new ConexaoDB();
        String sql = "DELETE FROM CONTATO R WHERE R.ID = ?";
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

    public Contato findById(String id) {
        conexao = new ConexaoDB();
        Contato reg = new Contato();
        String sql = "SELECT * FROM CONTATO R WHERE R.ID = ?";
        try {
            PreparedStatement ps = conexao.connect().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                reg.setId(rs.getString("ID"));
                reg.setTipo(rs.getInt("TIPO"));
                reg.setDescricao(rs.getString("DESCRICAO"));
                reg.setPreferencial(rs.getBoolean("PREFERENCIAL"));
                reg.setRegistro_id(rs.getString("REGISTRO_ID"));
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
