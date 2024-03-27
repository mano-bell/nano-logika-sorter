package de.nanologika.input;

import de.nanologika.model.SortConfig;
import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SortInputTest {
  @Mock OutputHandler outputHandler;
  @Mock InputHandler inputHandler;
  @InjectMocks private SortInput sortInput;

  private void start(String... inputs) {
    OngoingStubbing<String> stubbing =
        when(inputHandler.promptForInput(anyString(), anyBoolean(), anyBoolean()));
    for (String input : inputs) {
      stubbing = stubbing.thenReturn(input);
    }
  }

  @Test
  void testObtainSortConfigList_DefaultSortApplied() {
    start("");
    sortInput.obtainInput();
    List<SortConfig> sortConfigList = sortInput.getInputList();
    assertTrue(sortConfigList.isEmpty());
  }

  @Test
  void testObtainSortConfigList_ValidSequenceInput() {
    String[] input =
        new String[] {
          "1,2,3", "STRING", "ASCENDING", "NUMBER", "DESCENDING", "STRING", "DESCENDING"
        };
    start(input);
    sortInput.obtainInput();
    List<SortConfig> sortConfigList = sortInput.getInputList();

    // Assert the result
    assertEquals(3, sortConfigList.size());

    assertEquals(1, sortConfigList.get(0).getFieldIndex());
    assertEquals(true, sortConfigList.get(0).isString());
    assertEquals(true, sortConfigList.get(0).isAscending());

    assertEquals(2, sortConfigList.get(1).getFieldIndex());
    assertEquals(false, sortConfigList.get(1).isString());
    assertEquals(false, sortConfigList.get(1).isAscending());

    assertEquals(3, sortConfigList.get(2).getFieldIndex());
    assertEquals(true, sortConfigList.get(2).isString());
    assertEquals(false, sortConfigList.get(2).isAscending());
  }
}
