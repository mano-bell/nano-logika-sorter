package de.nanologika.input;

import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressInputTest {
  @Mock OutputHandler outputHandler;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  InputHandler inputHandler;

  @InjectMocks private AddressInput addressInput;

  private void start(String... inputs) {
    OngoingStubbing<String> stubbing =
        when(inputHandler.promptForInput(anyString(), anyBoolean(), anyBoolean()));
    for (String input : inputs) {
      stubbing = stubbing.thenReturn(input);
    }
  }

  @Test
  public void testObtainAddresses_WithValidInput() {
    String[] input =
        new String[] {
          "ID123-456_789GB_VVL", "ID456-789_123MB_TW", "ID789-123_456GB_VVLTW", ""
        }; // Empty line to end input
    start(input);
    addressInput.obtainInput();
    List<String> addresses = addressInput.getInputList();

    assertNotNull(addresses);
    assertEquals(3, addresses.size());
    assertTrue(addresses.contains("ID123-456_789GB_VVL"));
    assertTrue(addresses.contains("ID456-789_123MB_TW"));
    assertTrue(addresses.contains("ID789-123_456GB_VVLTW"));

    assertTrue(addressInput.isCustom()); // All addresses match the regex pattern
  }

  @Test
  public void testObtainAddresses_WithInvalidInput() {
    String[] input =
        new String[] {
          "ID123-456_789GB_VVL",
          "InvalidAddress", // Invalid address
          "ID789-123_456GB_VVLTW",
          ""
        }; // Empty line to end input
    start(input);
    addressInput.obtainInput();
    List<String> addresses = addressInput.getInputList();

    assertNotNull(addresses);
    assertEquals(3, addresses.size());
    assertTrue(addresses.contains("ID123-456_789GB_VVL"));
    assertTrue(addresses.contains("InvalidAddress")); // Invalid address is still included
    assertTrue(addresses.contains("ID789-123_456GB_VVLTW"));

    assertFalse(addressInput.isCustom()); // Not all addresses match the regex pattern
  }
}
