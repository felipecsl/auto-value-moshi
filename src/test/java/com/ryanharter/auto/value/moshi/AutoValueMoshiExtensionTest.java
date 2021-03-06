package com.ryanharter.auto.value.moshi;

import com.google.auto.value.processor.AutoValueProcessor;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

public class AutoValueMoshiExtensionTest {

  private JavaFileObject serializedName;

  @Before public void setup() {
    serializedName = JavaFileObjects.forSourceString("com.ryanharter.auto.value.moshi.SerializedName", ""
        + "package com.ryanharter.auto.value.moshi;\n"
        + "import java.lang.annotation.Retention;\n"
        + "import java.lang.annotation.Target;\n"
        + "import static java.lang.annotation.ElementType.METHOD;\n"
        + "import static java.lang.annotation.ElementType.PARAMETER;\n"
        + "import static java.lang.annotation.ElementType.FIELD;\n"
        + "import static java.lang.annotation.RetentionPolicy.SOURCE;\n"
        + "@Retention(SOURCE)\n"
        + "@Target({METHOD, PARAMETER, FIELD})\n"
        + "public @interface SerializedName {\n"
        + "  String value();\n"
        + "}");
  }

  @Test public void simple() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
            + "package test;\n"
            + "import com.squareup.moshi.Json;\n"
            + "import com.ryanharter.auto.value.moshi.SerializedName;\n"
            + "import com.google.auto.value.AutoValue;\n"
            + "import java.util.Map;\n"
            + "import java.util.Set;\n"
            + "@AutoValue public abstract class Test {\n"
            // Reference type
            + "public abstract String a();\n"
            // Array type
            + "public abstract int[] b();\n"
            // Primitive type
            + "public abstract int c();\n"
            // SerializedName // TODO uncomment this once the target is updated in Moshi
            + "/*@Json(name=\"_D\")*/ public abstract String d();\n"
            // Parametrized type, multiple parameters
            + "public abstract Map<String, Number> e();\n"
            // Parametrized type, single parameter
            + "public abstract Set<String> f();\n"
            // Nested parameterized type
            + "public abstract Map<String, Set<String>> g();\n"
            + "}\n"
    );

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Test", ""
            + "package test;\n"
            + "\n"
            + "import com.squareup.moshi.JsonAdapter;\n"
            + "import com.squareup.moshi.JsonReader;\n"
            + "import com.squareup.moshi.JsonWriter;\n"
            + "import com.squareup.moshi.Moshi;\n"
            + "import com.squareup.moshi.Types;\n"
            + "import java.io.IOException;\n"
            + "import java.lang.Integer;\n"
            + "import java.lang.Number;\n"
            + "import java.lang.Override;\n"
            + "import java.lang.String;\n"
            + "import java.lang.annotation.Annotation;\n"
            + "import java.lang.reflect.Type;\n"
            + "import java.util.Map;\n"
            + "import java.util.Set;\n"
            + "\n"
            + "final class AutoValue_Test extends $AutoValue_Test {\n"
            + "  AutoValue_Test(String a, int[] b, int c, String d, Map<String, Number> e, Set<String> f, Map<String, Set<String>> g) {\n"
            + "    super(a, b, c, d, e, f, g);\n"
            + "  }\n"
            + "\n"
            + "  public static AutoValue_TestJsonAdapterFactory typeAdapterFactory() {\n"
            + "    return new AutoValue_TestJsonAdapterFactory();\n"
            + "  }\n"
            + "\n"
            + "  public static final class AutoValue_TestJsonAdapterFactory implements JsonAdapter.Factory {\n"
            + "    @Override\n"
            + "    public JsonAdapter<Test> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {\n"
            + "      if (!type.equals(Test.class)) return null;\n"
            + "      return (JsonAdapter<Test>) new AutoValue_TestJsonAdapter(moshi);\n"
            + "    }\n"
            + "  }\n"
            + "  \n"
            + "  public static final class AutoValue_TestJsonAdapter extends JsonAdapter<Test> {\n"
            + "  \n"
            + "    private final JsonAdapter<String> aAdapter;\n"
            + "    private final JsonAdapter<int[]> bAdapter;\n"
            + "    private final JsonAdapter<Integer> cAdapter;\n"
            + "    private final JsonAdapter<String> dAdapter;\n"
            + "    private final JsonAdapter<Map<String, Number>> eAdapter;\n"
            + "    private final JsonAdapter<Set<String>> fAdapter;\n"
            + "    private final JsonAdapter<Map<String, Set<String>>> gAdapter;\n"
            + "  \n"
            + "    public AutoValue_TestJsonAdapter(Moshi moshi) {\n"
            + "      this.aAdapter = moshi.adapter(String.class);\n"
            + "      this.bAdapter = moshi.adapter(int[].class);\n"
            + "      this.cAdapter = moshi.adapter(Integer.class);\n"
            + "      this.dAdapter = moshi.adapter(String.class);\n"
            + "      this.eAdapter = moshi.adapter(Types.newParameterizedType(Map.class, String.class, Number.class));\n"
            + "      this.fAdapter = moshi.adapter(Types.newParameterizedType(Set.class, String.class));\n"
            + "      this.gAdapter = moshi.adapter(Types.newParameterizedType(Map.class, String.class, Types.newParameterizedType(Set.class, String.class)));\n"
            + "    }\n"
            + "  \n"
            + "    @Override public Test fromJson(JsonReader reader) throws IOException {\n"
            + "      reader.beginObject();\n"
            + "      String a = null;\n"
            + "      int[] b = null;\n"
            + "      Integer c = null;\n"
            + "      String d = null;\n"
            + "      Map<String, Number> e = null;\n"
            + "      Set<String> f = null;\n"
            + "      Map<String, Set<String>> g = null;"
            + "      while (reader.hasNext()) {\n"
            + "        String _name = reader.nextName();\n"
            + "        if (\"a\".equals(_name)) {\n"
            + "          a = aAdapter.fromJson(reader);\n"
            + "        } else if (\"b\".equals(_name)) {\n"
            + "          b = bAdapter.fromJson(reader);\n"
            + "        } else if (\"c\".equals(_name)) {\n"
            + "          c = cAdapter.fromJson(reader);\n"
            + "        } else if (\"d\".equals(_name)) {\n"
            + "          d = dAdapter.fromJson(reader);\n"
            + "        } else if (\"e\".equals(_name)) {\n"
            + "          e = eAdapter.fromJson(reader);\n"
            + "        } else if (\"f\".equals(_name)) {\n"
            + "          f = fAdapter.fromJson(reader);\n"
            + "        } else if (\"g\".equals(_name)) {\n"
            + "          g = gAdapter.fromJson(reader);\n"
            + "        }\n"
            + "      }\n"
            + "      reader.endObject();\n"
            + "      return new AutoValue_Test(a, b, c, d, e, f, g);\n"
            + "    }\n"
            + "  \n"
            + "    @Override public void toJson(JsonWriter writer, Test value) throws IOException {\n"
            + "      writer.beginObject();\n"
            + "      writer.name(\"a\");\n"
            + "      aAdapter.toJson(writer, value.a());\n"
            + "      writer.name(\"b\");\n"
            + "      bAdapter.toJson(writer, value.b());\n"
            + "      writer.name(\"c\");\n"
            + "      cAdapter.toJson(writer, value.c());\n"
            + "      writer.name(\"d\");\n"
            + "      dAdapter.toJson(writer, value.d());\n"
            + "      writer.name(\"e\");\n"
            + "      eAdapter.toJson(writer, value.e());\n"
            + "      writer.name(\"f\");\n"
            + "      fAdapter.toJson(writer, value.f());\n"
            + "      writer.name(\"g\");\n"
            + "      gAdapter.toJson(writer, value.g());\n"
            + "      writer.endObject();\n"
            + "    }\n"
            + "  }"
            + "}"
    );

    assertAbout(javaSources())
        .that(Arrays.asList(serializedName, source))
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }
}