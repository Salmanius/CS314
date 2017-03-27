package main.java.edu.csu2017sp314.dtr17.View;

/**
 * Created by mjdun on 3/20/2017.
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class OptionsList extends JList
{
    protected static final String[] OPTIONS_STRINGS = {
            "Mileage",
            "Names",
            "ID",
            "2-Op",
            "3-Op"
    };

    public enum OPTIONS {
        MILEAGE,
        NAMES,
        ID,
        TWO_OP,
        THREE_OP
    }

    protected static Border noFocusBorder =
            new EmptyBorder(1, 1, 1, 1);

    public OptionsList()
    {
        setCellRenderer(new CellRenderer());

        addMouseListener(new MouseAdapter()
             {
                 public void mousePressed(MouseEvent e)
                 {
                     int index = locationToIndex(e.getPoint());

                     if (index != -1) {
                         JCheckBox checkbox = (JCheckBox)
                                 getModel().getElementAt(index);
                         checkbox.setSelected(
                                 !checkbox.isSelected());
                         repaint();
                     }
                 }
             }
        );

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JCheckBox[] checkBoxes = new JCheckBox[OPTIONS_STRINGS.length];
        for(int i = 0; i < OPTIONS_STRINGS.length; ++i){
            checkBoxes[i] = new JCheckBox(OPTIONS_STRINGS[i]);
        }
        setListData(checkBoxes);
        //setListData(OPTIONS);
    }

    public boolean getCheckedState(OPTIONS options ){
        return ((JCheckBox)getModel().getElementAt(options.ordinal())).isSelected();

    }

    protected class CellRenderer implements ListCellRenderer
    {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus)
        {
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground(isSelected ?
                    getSelectionBackground() : getBackground());
            checkbox.setForeground(isSelected ?
                    getSelectionForeground() : getForeground());
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ?
                    UIManager.getBorder(
                            "List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }

}
