/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author PcProgram
 */
public class FXMLDocumentController implements Initializable {
  
    private MediaPlayer mediaPlayer;
   
    @FXML
     private MediaView mediaView;
    
     private String filePath;
     @FXML
     private Slider slider;
     
     @FXML
     private Slider seeSlider;
     
    @FXML
    private void handleButtonAction(ActionEvent event) {
    FileChooser fileChoser=new FileChooser();
     FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a file (*.mp4)","*.mp4"); 
     fileChoser.getExtensionFilters().add(filter);
      
      File file=fileChoser.showOpenDialog(null);
      filePath=file.toURI().toString();
      
      if(filePath!=null){
          Media media=new Media(filePath);
          mediaPlayer= new MediaPlayer(media);
          mediaView.setMediaPlayer(mediaPlayer);
              DoubleProperty width=mediaView.fitWidthProperty();
              DoubleProperty height=mediaView.fitHeightProperty();
               
                    width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                    height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
                    
                    slider.setValue(mediaPlayer.getVolume() *100);
                    slider.valueProperty().addListener(new InvalidationListener() {
              @Override
              public void invalidated(Observable observable) {
              mediaPlayer.setVolume(slider.getValue() /100);
                  
              }
          });
    
       mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
              @Override
              public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                
                  seeSlider.setValue(newValue.toSeconds());
              
              
              }
              });
       
           seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>(){
              @Override
              public void handle(MouseEvent event) {
                  
                  mediaPlayer.seek(Duration.seconds(seeSlider.getValue()));
                }
           });
                    
                      
          mediaPlayer.play();
      }
    }
    
    @FXML
    private void PauseVideo(ActionEvent event){
         mediaPlayer.pause();
    }
    
      @FXML
    private void PlayVideo(ActionEvent event){
         mediaPlayer.play();
         mediaPlayer.setRate(1);
    }
    
      @FXML
    private void StopVideo(ActionEvent event){
         mediaPlayer.stop();
    }
    
      @FXML
    private void FastVideo(ActionEvent event){
         mediaPlayer.setRate(1.5);
    }
    
       @FXML
    private void FasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    
       @FXML
    private void SlowtVideo(ActionEvent event){
        mediaPlayer.setRate(.75);
    }
     
       @FXML
    private void SlowerVideo(ActionEvent event){
       mediaPlayer.setRate(.5); 
    }
    
       @FXML
    private void ExitVideo(ActionEvent event){
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
