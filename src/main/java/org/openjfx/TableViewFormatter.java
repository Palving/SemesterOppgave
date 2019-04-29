
package org.openjfx;

import Model.Registrering.Register;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TableViewFormatter {
    
    private Register register=Register.getInstance();
    private String[] attributter=null;
    private TableView table;
    
     public TableView hentData(ObservableList<Object> liste, String type){
       
         
       attributter=getTableViewAttributter(type);
       table=new TableView();
       
       TableColumn[] columns=new TableColumn[attributter.length];
     
       int teller=0;
       for (String att : attributter){
           columns[teller]=new TableColumn(att);
           columns[teller].setCellValueFactory(new PropertyValueFactory<Object, Object>(attributter[teller]));
          
           teller++;
       }
    
    table.setItems(liste);
    for (TableColumn t : columns){
        table.getColumns().addAll(t);
    }
  
     table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setEditable(true);
        return table;
        
       
        }
     
     protected String[] getTableViewAttributter(String valgt){
      
         
    /* String[] artistAttributes={"Fornavn","Etternavn","Tlf","typeArtist"};
      String[] lokaleAttributes={"lokaleNavn","antallPlasser"};
      String[] arrangAttributes={"type", "navnPaaArrangement","program", "billettPris", "Tidspunkt"};
      String[] kontaktPersonAttributes={"fornavn","etternavn","tlf","firma","info","nettSide"};
      String[] billettAttributes={"plassNummer","lokaleNavn","dato", "kundeTlf","arrangementNavn"};*/
    String[] artistAttributes={"Fornavn","Etternavn","Tlf","typeArtist"};
      String[] lokaleAttributes={"lokaleNavn","antallPlasser"};
      String[] arrangAttributes={"Artist","KontaktPerson","sted","dato","tidspunkt", "type", "navnPaaArrangement","program", "billettPris","billettSalg"};
      String[] kontaktPersonAttributes={"fornavn","etternavn","tlf","nettSide","firma","info"};
      String[] billettAttributes={"plassNummer","kundeTlf","lokaleNavn","dato","arrangementNavn"};
      
      switch(valgt){
          case "Artist":
               attributter=artistAttributes;
               break;
          case "Lokale":
               attributter=lokaleAttributes;
               break;
          case "Arrangement":
              attributter=arrangAttributes;
              break;
          case "KontaktPerson":
              attributter=kontaktPersonAttributes;
              break;
          case "Billett":
              attributter=billettAttributes;
              break;
          
      }
      return attributter;
 }
   
  
   public TableView visData(String valgt){
     
   switch(valgt){
       case "Artist":
           ObservableList<Object> artister=FXCollections.observableArrayList(register.getArtister());
           table=hentData(artister,"Artist");
           break;
       case "Lokale":
           ObservableList<Object> lokale=FXCollections.observableArrayList(register.getLokale());
           hentData(lokale,"Lokale");
           break;
       case "Arrangement":
           ObservableList<Object> arrangement=FXCollections.observableArrayList(register.getArrangement());
           hentData(arrangement,"Arrangement");
           break;
       case "KontaktPerson":
           ObservableList<Object> kontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
           hentData(kontaktPerson,"KontaktPerson");
           break;
       case "Billett":
           ObservableList<Object> billett=FXCollections.observableArrayList(register.getBillett());
           hentData(billett,"Billett");
           break;
        }   
   return table;
   }
    
}
