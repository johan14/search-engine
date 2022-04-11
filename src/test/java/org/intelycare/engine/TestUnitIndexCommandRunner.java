package org.intelycare.engine;

import com.intelycare.engine.exceptions.BadCommandException;
import com.intelycare.engine.models.commands.Command;
import com.intelycare.engine.models.commands.IndexCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class TestUnitIndexCommandRunner {

    @Test
    public void shouldIndexCorrectly() throws BadCommandException, IOException {
        Command indexCommand = new IndexCommand();
        assertEquals("index ok 1", indexCommand.executeCommand("index 1 test"));
    }

    @Test
    public void shouldThrowExceptionBadCommand(){
        Command indexCommand = new IndexCommand();
        assertThrows(BadCommandException.class,() -> indexCommand.executeCommand("index test"));
    }
}
