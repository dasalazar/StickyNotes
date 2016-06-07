package com.example.dodo.myapplication;import android.content.Intent;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.util.Log;import android.view.View;import android.widget.EditText;import android.widget.Toast;import dao.StickNoteDao;import dao.UsuarioDao;import model.StickNote;import model.Usuario;public class StickNotesCrud extends AppCompatActivity {    StickNoteDao stickNoteDao;    private EditText edtTitulo, edtNota;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_stick_notes_crud);        edtTitulo = (EditText) findViewById(R.id.editTextTitulo);        edtNota = (EditText) findViewById(R.id.editTextNota);    }    public void cadastroNovoStickNote(View v){        stickNoteDao = new StickNoteDao(this);        StickNote stickNote = new StickNote();        boolean validacao = true;        String titulo = edtTitulo.getText().toString();        String nota = edtNota.getText().toString();        if(titulo == null || titulo.equals("")) {            validacao = false;            edtTitulo.setError(getString(R.string.stick_note_titulo));        }        if(nota == null || nota.equals("")) {            validacao = false;            edtNota.setError(getString(R.string.stick_note_nota));        }        if(validacao) {            stickNote.setNota(nota);            stickNote.setTitulo(titulo);            stickNote.setIndConcluido(0);                long resultSave = stickNoteDao.saveStickNote(stickNote);                Intent resultIntent = new Intent(this,StickNotes.class);                startActivity(resultIntent);                setContentView(R.layout.activity_stick_notes2);                finish();        }    }    public void sairCadastro(View v){        Intent it = new Intent(this, StickNotes.class);        startActivity(it);        setContentView(R.layout.activity_stick_notes2);        finish();    }}