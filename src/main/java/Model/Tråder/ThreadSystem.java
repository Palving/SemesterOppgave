
package Model.Tråder;

import javafx.concurrent.Task;
import org.openjfx.FeilmeldingSystem;


public class ThreadSystem extends Task<Void> {
    private Runnable runMeWhenDone;

  public ThreadSystem(Runnable doneFunc){
      this.runMeWhenDone=doneFunc;
  }
  
  @Override
  protected Void call() throws Exception{
      try {
          Thread.sleep(5000);
         
      }
      catch(InterruptedException e){
          
      }
      return null;
  }
  
    @Override
    protected void succeeded() {
        runMeWhenDone.run();
        System.out.println("succeeded");
        
    }
    
    @Override
    protected void failed(){
       FeilmeldingSystem.visFeilmelding(getException().getMessage());
    }
        
    
}
