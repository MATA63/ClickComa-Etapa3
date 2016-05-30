/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

public class Cardapio {
    private List<Item> listItem;

    /**
     * @return the listItem
     */
    public List<Item> getListItem() {
        return listItem;
    }

    /**
     * @param listItem the listItem to set
     */
    public void setListItem(List<Item> listItem) {
        this.listItem = listItem;
    }
    
    /**
     *
     * @param listItem
     */
    public Cardapio(List<Item> listItem){
        setListItem(listItem);
    }
    
    public Cardapio() {
    }

}
