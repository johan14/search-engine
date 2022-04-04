package engine.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;

public class PropertiesHolder {

  private Properties prop;

  @Inject
  @Singleton
  public PropertiesHolder(){
    try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {

      prop = new Properties();
      prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }


  public Properties getProp() {
    return prop;
  }

  public void setProp(Properties prop) {
    this.prop = prop;
  }
}
