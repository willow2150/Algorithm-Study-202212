import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MEMORY_BYTE_CAPACITY = 32;
        final int ADDER_BOUNDARY = (1 << 8) - 1;
        final int PC_BOUNDARY = (1 << 5) - 1;
        final int STA = 0, LDA = 1, BEQ = 2, NOP = 3, DEC = 4, INC = 5, JMP = 6, HLT = 7;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        while (true) {
            int[] memory = new int[MEMORY_BYTE_CAPACITY];
            int pc = 0, adder = 0;

            String cmd = reader.readLine();
            if (cmd == null || cmd.isBlank()) {
                break;
            }
            memory[0] = Integer.parseInt(cmd, 2);
            for (int cmdIdx = 1; cmdIdx < MEMORY_BYTE_CAPACITY; cmdIdx++) {
                memory[cmdIdx] = Integer.parseInt(reader.readLine(), 2);
            }

            while (true) {
                int cmdCode = memory[pc++];
                int commandType = cmdCode >> 5;
                int operand = cmdCode & 31;

                if (commandType == HLT) {
                    output.append(
                            String.format(
                                    "%08d",
                                    Integer.parseInt(Integer.toBinaryString(adder))
                            )
                    ).append('\n');
                    break;
                }

                pc &= PC_BOUNDARY;

                switch (commandType) {
                    case STA -> memory[operand] = adder;
                    case LDA -> adder = memory[operand];
                    case BEQ -> pc = (adder == 0 ? operand : pc);
                    case DEC -> adder = (adder + ADDER_BOUNDARY) & ADDER_BOUNDARY;
                    case INC -> adder = (adder + 1) & ADDER_BOUNDARY;
                    case JMP -> pc = operand;
                }
            }
        }

        System.out.print(output);
    }
}
