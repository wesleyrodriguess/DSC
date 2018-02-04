/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.MercadoOfertas.Aplicacao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class RepositorioBuilder {
    
    private static InputStream arquivo;  // Representa o arquivo físico no disco
    private static Properties prop;      // Responsável por carregar as configurações dentro do arquivo
    
    
    static {
        try {
            arquivo = RepositorioBuilder.class.getResourceAsStream("/config.properties"); //new FileInputStream("config.properties");
            prop = new Properties();
            prop.load(arquivo);
            
        } catch (IOException ex) {
            
            Logger.getLogger(RepositorioBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static PessoaRepositorio pessoa;
    
    public static PessoaRepositorio getPessoaRepositorio(){
        if(pessoa == null){
            try {
                
                // Carrega a classe
                Class obj = Class.forName(prop.getProperty("PessoaRepositorio"));
                
                // Cria uma nova instância da classe
                pessoa = (PessoaRepositorio)obj.newInstance();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RepositorioBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(RepositorioBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RepositorioBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pessoa;
    }
    
}
