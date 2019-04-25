
package Model.Tråder;

import javafx.concurrent.Task;


public class ThreadSystem extends Task<Void> {
    private Runnable runMeWhenDone;

  public ThreadSystem(Runnable doneFunc){
      this.runMeWhenDone=doneFunc;
  }
  
  @Override
  protected Void call() throws Exception{
      try {
          Thread.sleep(15000);
         
      }
      catch(InterruptedException e){
          
      }
      System.out.print("tråd ferdig");
      return null;
  }
  
    @Override
    protected void succeeded() {
        runMeWhenDone.run();
        System.out.println("succeeded");
        
    }
    
}
