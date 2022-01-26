/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

public class Tela_principal implements Initializable {

    private double xOffset = 0; 
    private double yOffset = 0;  
    
    @FXML javafx.scene.Node rootNode;
    @FXML javafx.scene.Node topmenuNode;
    @FXML javafx.scene.image.ImageView maxrestore_image;


    @FXML
    public void EventConsume(javafx.event.Event event){
        event.consume();
    }
    
    @FXML
    public void topmenuNode_Mouse_Clicked(javafx.scene.input.MouseEvent t){
        if(t.getButton().equals(javafx.scene.input.MouseButton.PRIMARY)){
            //System.out.println("Clicked");
            if(t.getClickCount() == 2){
                //System.out.println("Double clicked");
                button_O_Clicked(t);
            }
        }
    }
    
    @FXML
    public void topmenuNode_pressed_drag_whole_app(javafx.scene.input.MouseEvent event){
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
    }
    
    
    boolean draggedFromMaximizedStage = false ;
    double oldStageWidth ;
    double oldStageHeight ;
    
    @FXML
    public void topmenuNode_released_drag_whole_app(javafx.scene.input.MouseEvent event){
        if (event.getScreenY() < event.getSceneY()){
            javafx.stage.Stage stage = get_Stage_From_This_Controller();
            maximizeStage(stage);
            stage.setX(0);
            stage.setY(0);
            draggedFromMaximizedStage = false;
        }   else if (event.getScreenX() < event.getSceneX()){
            javafx.stage.Stage stage = get_Stage_From_This_Controller();
            if(!stage.isMaximized()){
                maximizeStage(stage);
            }
            double maxWidth = stage.getWidth();
            double maxHeight = stage.getHeight(); 
            restoreStage(stage);
            stage.setX(0);
            stage.setY(0);
            stage.setWidth(maxWidth/2);
            stage.setHeight(maxHeight);
        }
    }
    
    @FXML
    public void topmenuNode_dragged_drag_whole_app(javafx.scene.input.MouseEvent event){
        if (event.getScreenY() <= event.getSceneY()){
            rootNode.setEffect(
                    new javafx.scene.effect.Reflection()
            );
        } else if (event.getScreenX() <= event.getSceneX()){
            rootNode.setEffect(
                    new javafx.scene.effect.Reflection()
            );
        } 
        //else{
            if (get_Stage_From_This_Controller().isMaximized()){
                oldStageWidth = get_Stage_From_This_Controller().getWidth();
                oldStageHeight = get_Stage_From_This_Controller().getHeight();
                button_O_Clicked(event);
                draggedFromMaximizedStage = true;
 
            } else{
                
                    if (draggedFromMaximizedStage){
                        xOffset *= get_Stage_From_This_Controller().getWidth() / oldStageWidth;
                        yOffset *= get_Stage_From_This_Controller().getHeight() / oldStageHeight;
                        draggedFromMaximizedStage = false;
                    }
                    get_Stage_From_This_Controller().setX(event.getScreenX() - xOffset );
                    get_Stage_From_This_Controller().setY(event.getScreenY() - yOffset ) ;    
                
            }
        //}    
    }

    private javafx.stage.Stage get_Stage_From_This_Controller(){
        return (javafx.stage.Stage)(rootNode.getScene().getWindow());
    }
            
    private javafx.stage.Stage get_Stage_From_Node_Event(javafx.event.Event e){
        return (javafx.stage.Stage)(((javafx.scene.Node) e.getSource()).getScene().getWindow());
    }
    
    


    @FXML
    public void button_M_Clicked(javafx.event.Event e){
        get_Stage_From_Node_Event(e).setIconified(true);
    }
    
    @FXML
    public void button_O_Clicked(javafx.event.Event e){
        javafx.stage.Stage stage = get_Stage_From_Node_Event(e);
        boolean max_property_of_stage = stage.maximizedProperty().get();
        if(max_property_of_stage){
            restoreStage(stage);
        }else{
            maximizeStage(stage);
        }
    }
    
    private void maximizeStage(javafx.stage.Stage stage){
        stage.setMaximized(true);
        maxrestore_image.setImage(new javafx.scene.image.Image( getClass().getResourceAsStream("img/window-icons/restore-w-30.png") ));
    }
    
    private void restoreStage(javafx.stage.Stage stage){
        stage.setMaximized(false);
        maxrestore_image.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("img/window-icons/max-w-30.png")));
        //stage.setX(10); stage.setY(10);
    }

    @FXML
    public void button_X_Clicked(javafx.event.Event e){
        get_Stage_From_Node_Event(e).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
