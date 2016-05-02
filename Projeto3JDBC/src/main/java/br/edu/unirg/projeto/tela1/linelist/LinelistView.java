/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.projeto.tela1.linelist;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author rogerio
 */
public class LinelistView extends FXMLView {
    
    public LinelistPresenter getRealPresenter() {
        return (LinelistPresenter) super.getPresenter();
    }
    
}
