package dao;import android.content.ContentValues;import android.content.Context;import android.database.Cursor;import android.database.sqlite.SQLiteDatabase;import android.provider.ContactsContract;import java.util.ArrayList;import java.util.List;import model.Usuario;/** * Created by dodo on 20/05/16. */public class UsuarioDao {    private DataBaseHelper dataBaseHelper;    private SQLiteDatabase database;    public UsuarioDao (Context context){        dataBaseHelper = new DataBaseHelper(context);    }    public Usuario getUsuarioToModel(Cursor cursor){        Usuario usuarioRetorno = new Usuario();        usuarioRetorno.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.SQLUsuario.ID)));        return usuarioRetorno;    }    private SQLiteDatabase getDatabase(){        if(database == null){            database = dataBaseHelper.getWritableDatabase();        }        return database;    }    public List<Usuario> listarUsuarios(){        Cursor cursor = getDatabase().query(DataBaseHelper.SQLUsuario.TABELA,                                            DataBaseHelper.SQLUsuario.COLUNAS,                                            null, null,null,null,null                                            );        List<Usuario> listaRetorno = new ArrayList<>();        while (cursor.moveToNext()){            Usuario usuario = getUsuarioToModel(cursor);            listaRetorno.add(usuario);        }        return listaRetorno;    }    public long saveUsuario(Usuario usuario){        ContentValues dados = new ContentValues();        dados.put(DataBaseHelper.SQLUsuario.NOME,usuario.getNome());        dados.put(DataBaseHelper.SQLUsuario.LOGIN,usuario.getLogin());        dados.put(DataBaseHelper.SQLUsuario.SENHA,usuario.getSenha());        if(usuario.getId() > 0){            return getDatabase().update(DataBaseHelper.SQLUsuario.TABELA, dados,                    " _id = ?", new String[] { String.valueOf(usuario.getId()) });        }        else{            return getDatabase().insert(DataBaseHelper.SQLUsuario.TABELA,null,dados);        }    }    public boolean deleteUsuario(int id){        return getDatabase().delete(DataBaseHelper.SQLUsuario.TABELA,                " _id = ? ", new String[] { Integer.toString(id) }) > 0;    }    public Usuario getUsuarioById(int id){        Usuario usuarioRetorno = new Usuario();        Cursor cursor = getDatabase().query(DataBaseHelper.SQLUsuario.TABELA,                DataBaseHelper.SQLUsuario.COLUNAS, " _id = ?", new String[] { Integer.toString(id)},null,null,null);        if(cursor.moveToNext()){            usuarioRetorno = this.getUsuarioToModel(cursor);        }        return usuarioRetorno;    }    public boolean logar(String usuario, String senha){        Cursor cursor = getDatabase().query(DataBaseHelper.SQLUsuario.TABELA,null,                "login = ? AND senha = ?",                new String[]{usuario,senha},                null,null,null);        if(cursor.moveToNext()){            return true;        }        return false;    }    public void closeConn(){        database.close();        database = null;    }}