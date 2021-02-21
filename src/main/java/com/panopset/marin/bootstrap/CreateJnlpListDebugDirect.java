package com.panopset.marin.bootstrap;

import java.io.IOException;
import com.panopset.compat.Fileop;
import com.panopset.compat.Stringop;
import com.panopset.util.WebLocal;

public class CreateJnlpListDebugDirect {

  public static void main(String... args) throws IOException {
    new CreateJnlpListDebugDirect().go();
  }

  private void go() throws IOException {
    String httpHome = WebLocal.getWebLocalDirectory();
    CreatePanopsetInfoFile.main(Fileop.combinePaths(Stringop.USER_HOME,
      "panopset.jar"), Fileop.combinePaths(httpHome, "gen/json/pci.json"));
    CreateJnlpList.main("src/main/java/com/panopset/marin/jnlp/apps",
      Fileop.combinePaths(httpHome, "gen/json/pan_apps.json")
    );
    CreateJnlpList.main("src/main/java/com/panopset/marin/jnlp/games",
      Fileop.combinePaths(httpHome, "gen/json/pan_games.json")
    );
    CreateJnlpList.main("src/main/java/com/panopset/marin/jnlp/security",
      Fileop.combinePaths(httpHome, "gen/json/pan_security.json")
    );
  }

}
