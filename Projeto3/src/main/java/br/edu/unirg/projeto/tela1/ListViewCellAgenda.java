package br.edu.unirg.projeto.tela1;

import br.edu.unirg.projeto.bean.Agenda;
import br.edu.unirg.projeto.tela1.linelist.LinelistPresenter;
import br.edu.unirg.projeto.tela1.linelist.LinelistView;
import javafx.scene.control.ListCell;

/**
 *
 * @author rogerio
 */
public class ListViewCellAgenda  extends ListCell<Agenda> {
    
    public ListViewCellAgenda() {
    }

    @Override
    protected void updateItem(Agenda item, boolean empty) {
        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
        if(item != null && !empty) {
            LinelistView view = new LinelistView();
            LinelistPresenter presenter = view.getRealPresenter();
            presenter.loadObjAgenda(item);
            setGraphic(view.getView());
        } else {
            setGraphic(null);
        }
    }
    
}
