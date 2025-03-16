/*
 * The MIT License
 *
 * Copyright 2025 Guilherme.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.infox.dal;

import java.sql.*;

/**
 * Conexão com o banco de dados
 *
 * @author Guilherme
 */
public class ModuloConexao {

    //método responsável por estabelecer a conexão com o banco
    /**
     * Método responsável pela conexão com o banco
     *
     * @return conexao
     */
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // a linha abaixo chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando informações referente ao banco.
        String url = "jdbc:mysql://localhost:3306/dbinfox?characterEncoding=utf-8";
        String user = ""; //insira o usuario do mysq
        String password = ""; //insira a senha do mysql
        //criei quatro variaveis. uma fala qual é o drive e o tipo de bd, outro o caminho e nome do bd, outro o usuario , o outro a senha
        //estabelecendo a conexão com o banco.
        //tratamento de exceções. entre o java e o bd, tem a estrutura da rede. tem um caminho pro formulario chegar até i bd. pode ter muitos imprevistos, tipo queimar uma porta de o switch. por isso temos que dar tratamento de exceções.
        try {
            //caso dê certo
            Class.forName(driver); //executa o arquivo do drive
            conexao = DriverManager.getConnection(url, user, password); // a variavel que criamos lá em cima.
            //ele vai obter a conexão utilizando o url, usuario e senha.
            return conexao;

        } catch (Exception e) {
            // a linha abaixo serve de apoio pra esclarecer o erro.

            System.out.println(e); //imprime o erro pra mim, caso não conecte
            return null;

        }

    }

}
