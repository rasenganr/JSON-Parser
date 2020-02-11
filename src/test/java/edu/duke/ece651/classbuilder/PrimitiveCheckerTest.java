package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PrimitiveCheckerTest {
  @Test
  public void test_isPrimitiveOrString() {
    PrimitiveChecker pc = new PrimitiveChecker();
    assertEquals(pc.isPrimitiveOrString("int"), true);
    assertEquals(pc.isPrimitiveOrString("short"), true);
    assertEquals(pc.isPrimitiveOrString("long"), true);
    assertEquals(pc.isPrimitiveOrString("byte"), true);
    assertEquals(pc.isPrimitiveOrString("char"), true);
    assertEquals(pc.isPrimitiveOrString("float"), true);
    assertEquals(pc.isPrimitiveOrString("double"), true);
    assertEquals(pc.isPrimitiveOrString("boolean"), true);
    assertEquals(pc.isPrimitiveOrString("String"), true);
    assertEquals(pc.isPrimitiveOrString("Grade"), false);
    assertEquals(pc.isPrimitiveOrString("Integer"), false);
  }

  @Test
  public void test_isWrapperOrString() {
    PrimitiveChecker pc = new PrimitiveChecker();
    assertEquals(pc.isWrapperOrString("Integer"), true);
    assertEquals(pc.isWrapperOrString("Short"), true);
    assertEquals(pc.isWrapperOrString("Long"), true);
    assertEquals(pc.isWrapperOrString("Byte"), true);
    assertEquals(pc.isWrapperOrString("Character"), true);
    assertEquals(pc.isWrapperOrString("Float"), true);
    assertEquals(pc.isWrapperOrString("Double"), true);
    assertEquals(pc.isWrapperOrString("Boolean"), true);
    assertEquals(pc.isWrapperOrString("String"), true);
    assertEquals(pc.isWrapperOrString("Grade"), false);
    assertEquals(pc.isWrapperOrString("int"), false);
  }

  @Test
  public void test_toPrimitive() {
    PrimitiveChecker pc = new PrimitiveChecker();
    assertEquals(pc.toPrimitive("Integer"), "int");
    assertEquals(pc.toPrimitive("Short"), "short");
    assertEquals(pc.toPrimitive("Long"), "long");
    assertEquals(pc.toPrimitive("Byte"), "byte");
    assertEquals(pc.toPrimitive("Character"), "char");
    assertEquals(pc.toPrimitive("Float"), "float");
    assertEquals(pc.toPrimitive("Double"), "double");
    assertEquals(pc.toPrimitive("Boolean"), "boolean");
    assertEquals(pc.toPrimitive("Grade"), "Grade");
    assertEquals(pc.toPrimitive("int"), "int");
  }

}
