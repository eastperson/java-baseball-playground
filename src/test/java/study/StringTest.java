package study;
import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }
    @Test
    @DisplayName("\"1,2\"을 ,로 split 했을 때 1과 2로 잘 분리되는지")
    void split() {
        String[] actual = "1,2".split(",");
        assertThat(actual).contains("1", "2");
    }
    @Test
    @DisplayName("\"1\"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지")
    void splitExactly() {
        String[] actual = "1".split(",");
        assertThat(actual).containsExactly("1");
    }
    @Test
    @DisplayName("\"(1,2)\" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 \"1,2\"를 반환")
    void substring() {
        String given = "(1,2)";
        String actual = given.substring(1, given.length() - 1);
        assertThat(actual).isEqualTo("1,2");
    }
    @Test
    @DisplayName("\"abc\" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는지")
    void charAt() {
        String given = "abc";
        int indexOutOfRange = 10;
        char actual = given.charAt(0);
        assertThat(actual).isEqualTo('a');
        assertThatThrownBy(() -> {
            char throwableCode = given.charAt(indexOutOfRange);
        }).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: " + indexOutOfRange);
    }
}