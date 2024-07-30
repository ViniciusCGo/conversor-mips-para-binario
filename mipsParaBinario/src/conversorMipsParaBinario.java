import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class conversorMipsParaBinario {

    // Mapeamento de opcodes para binários
    private static final Map<String, String> OPCODES = new HashMap<>();
    private static final Map<String, String> FUNCT_CODES = new HashMap<>();
    private static final Map<String, String> REGISTER_CODES = new HashMap<>();

    static {
        // Instruções tipo R
        OPCODES.put("add", "000000");
        FUNCT_CODES.put("add", "100000");
        OPCODES.put("addu", "000000");
        FUNCT_CODES.put("addu", "100001");
        OPCODES.put("sub", "000000");
        FUNCT_CODES.put("sub", "100010");
        OPCODES.put("subu", "000000");
        FUNCT_CODES.put("subu", "100011");
        OPCODES.put("and", "000000");
        FUNCT_CODES.put("and", "100100");
        OPCODES.put("or", "000000");
        FUNCT_CODES.put("or", "100101");
        OPCODES.put("xor", "000000");
        FUNCT_CODES.put("xor", "100110");
        OPCODES.put("nor", "000000");
        FUNCT_CODES.put("nor", "100111");
        OPCODES.put("slt", "000000");
        FUNCT_CODES.put("slt", "101010");
        OPCODES.put("sltu", "000000");
        FUNCT_CODES.put("sltu", "101011");
        OPCODES.put("sll", "000000");
        FUNCT_CODES.put("sll", "000000");
        OPCODES.put("srl", "000000");
        FUNCT_CODES.put("srl", "000010");
        OPCODES.put("sra", "000000");
        FUNCT_CODES.put("sra", "000011");
        OPCODES.put("sllv", "000000");
        FUNCT_CODES.put("sllv", "000100");
        OPCODES.put("srlv", "000000");
        FUNCT_CODES.put("srlv", "000110");
        OPCODES.put("srav", "000000");
        FUNCT_CODES.put("srav", "000111");
        OPCODES.put("mfhi", "000000");
        FUNCT_CODES.put("mfhi", "000000");
        OPCODES.put("mthi", "000000");
        FUNCT_CODES.put("mthi", "001001");
        OPCODES.put("mflo", "000000");
        FUNCT_CODES.put("mflo", "000010");
        OPCODES.put("mtlo", "000000");
        FUNCT_CODES.put("mtlo", "001011");
        OPCODES.put("mult", "000000");
        FUNCT_CODES.put("mult", "011000");
        OPCODES.put("multu", "000000");
        FUNCT_CODES.put("multu", "011001");
        OPCODES.put("div", "000000");
        FUNCT_CODES.put("div", "011010");
        OPCODES.put("divu", "000000");
        FUNCT_CODES.put("divu", "011011");

        // Instruções tipo I
        OPCODES.put("lb", "100000");
        OPCODES.put("lh", "100001");
        OPCODES.put("lwl", "100010");
        OPCODES.put("lw", "100011");
        OPCODES.put("lbu", "100100");
        OPCODES.put("lhu", "100101");
        OPCODES.put("lwr", "100110");
        OPCODES.put("sb", "101000");
        OPCODES.put("sh", "101001");
        OPCODES.put("swl", "101010");
        OPCODES.put("sw", "101011");
        OPCODES.put("swr", "101100");
        OPCODES.put("addi", "001000");
        OPCODES.put("addiu", "001001");
        OPCODES.put("slti", "001010");
        OPCODES.put("sltiu", "001011");
        OPCODES.put("andi", "001100");
        OPCODES.put("ori", "001101");
        OPCODES.put("xori", "001110");
        OPCODES.put("lui", "001111");
        OPCODES.put("beq", "000100");
        OPCODES.put("bne", "000101");
        OPCODES.put("blez", "000110");
        OPCODES.put("bgtz", "000111");
        OPCODES.put("bltz", "001000");
        OPCODES.put("bgez", "001001");
        OPCODES.put("bltzal", "001010");
        OPCODES.put("bgezal", "001011");

        // Instruções tipo J
        OPCODES.put("j", "000010");
        OPCODES.put("jal", "000011");
        OPCODES.put("jr", "000000");
        FUNCT_CODES.put("jr", "001000");
        OPCODES.put("jalr", "000000");
        FUNCT_CODES.put("jalr", "001001");

        // Mapeamento de registradores
        REGISTER_CODES.put("$zero", "00000");
        REGISTER_CODES.put("$at", "00001");
        REGISTER_CODES.put("$v0", "00010");
        REGISTER_CODES.put("$v1", "00011");
        REGISTER_CODES.put("$a0", "00100");
        REGISTER_CODES.put("$a1", "00101");
        REGISTER_CODES.put("$a2", "00110");
        REGISTER_CODES.put("$a3", "00111");
        REGISTER_CODES.put("$t0", "01000");
        REGISTER_CODES.put("$t1", "01001");
        REGISTER_CODES.put("$t2", "01010");
        REGISTER_CODES.put("$t3", "01011");
        REGISTER_CODES.put("$t4", "01100");
        REGISTER_CODES.put("$t5", "01101");
        REGISTER_CODES.put("$t6", "01110");
        REGISTER_CODES.put("$t7", "01111");
        REGISTER_CODES.put("$s0", "10000");
        REGISTER_CODES.put("$s1", "10001");
        REGISTER_CODES.put("$s2", "10010");
        REGISTER_CODES.put("$s3", "10011");
        REGISTER_CODES.put("$s4", "10100");
        REGISTER_CODES.put("$s5", "10101");
        REGISTER_CODES.put("$s6", "10110");
        REGISTER_CODES.put("$s7", "10111");
        REGISTER_CODES.put("$t8", "11000");
        REGISTER_CODES.put("$t9", "11001");
        REGISTER_CODES.put("$k0", "11010");
        REGISTER_CODES.put("$k1", "11011");
        REGISTER_CODES.put("$gp", "11100");
        REGISTER_CODES.put("$sp", "11101");
        REGISTER_CODES.put("$fp", "11110");
        REGISTER_CODES.put("$ra", "11111");
    }

    public static void main(String[] args) {
        // Caminho para o arquivo de entrada e saída
        String inputFilePath = "C:\\Users\\vivic\\Desktop\\Mips\\instrucoes_mips.txt";
        String outputFilePath = "C:\\Users\\vivic\\Desktop\\Binario\\instrucoes_binarias.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String binaryInstruction = convertToBinary(line.trim());
                writer.write(binaryInstruction);
                writer.newLine();
            }

            System.out.println("Tradução concluída. Saída salva em " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para converter uma instrução MIPS para binário
    private static String convertToBinary(String instruction) {
        String[] parts = instruction.split("\\s+|,\\s*");
        String opcode = parts[0];

        if (OPCODES.containsKey(opcode)) {
            String opBinary = OPCODES.get(opcode);
            switch (opcode) {
                case "add":
                case "addu":
                case "sub":
                case "subu":
                case "and":
                case "or":
                case "xor":
                case "nor":
                case "slt":
                case "sltu":
                    // Instruções do tipo R
                    return opBinary + registerBinary(parts[2]) + registerBinary(parts[3]) +
                            registerBinary(parts[1]) + "00000" + FUNCT_CODES.get(opcode);

                case "sll":
                case "srl":
                case "sra":
                    // Instruções do tipo R com shift
                    return opBinary + registerBinary(parts[2]) + registerBinary(parts[1]) +
                            immediateBinary(parts[3], 5) + FUNCT_CODES.get(opcode);

                case "sllv":
                case "srlv":
                case "srav":
                case "mult":
                case "multu":
                case "div":
                case "divu":
                    // Instruções do tipo R especiais
                    return opBinary + registerBinary(parts[1]) + registerBinary(parts[2]) +
                            "00000" + "00000" + FUNCT_CODES.get(opcode);
                    
                case "mfhi":
                	return opBinary + "00000" + "00000" + registerBinary(parts[1]) + "00000" + FUNCT_CODES.get(opcode);
		
		case "mthi":
			return opBinary + registerBinary(parts[1]) + "00000" + "00000" + "00000" + FUNCT_CODES.get(opcode);
				
		case "mflo": 
			return opBinary + "00000" + "00000" + registerBinary(parts[1]) + "00000" + FUNCT_CODES.get(opcode);
				
		case "mtlo":
			return opBinary + registerBinary(parts[1]) + "00000" + "00000" + "00000" + FUNCT_CODES.get(opcode);


                case "lb":
                case "lh":
                case "lwl":
                case "lw":
                case "lbu":
                case "lhu":
                case "lwr":
                case "sb":
                case "sh":
                case "swl":
                case "sw":
                case "swr":
                    // Instruções do tipo I com carregamento/armazenamento
                    String[] offsetBase = parts[2].replace(")", "").split("\\(");
                    return opBinary + registerBinary(offsetBase[1]) + registerBinary(parts[1]) +
                            immediateBinary(offsetBase[0], 16);

                case "addi":
                case "addiu":
                case "slti":
                case "sltiu":
                case "andi":
                case "ori":
                case "xori":
                case "lui":
                case "beq":
                case "bne":
                case "blez":
                case "bgtz":
                case "bltz":
                case "bgez":
                case "bltzal":
                case "bgezal":
                    // Instruções do tipo I com imediato
                    return opBinary + registerBinary(parts[1]) + registerBinary(parts[2]) +
                            immediateBinary(parts[3], 16);

                case "j":
                case "jal":
                    // Instruções do tipo J
                    return opBinary + immediateBinary(parts[1], 26);

                case "jr":
                case "jalr":
                    // Instruções do tipo R com jump
                    return opBinary + registerBinary(parts[1]) + "00000" + "00000" + FUNCT_CODES.get(opcode);

                default:
                    throw new IllegalArgumentException("Instrução desconhecida: " + opcode);
            }
        } else {
            throw new IllegalArgumentException("Opcode desconhecido: " + opcode);
        }
    }

    // Método para converter um registrador para binário
    private static String registerBinary(String register) {
        if (REGISTER_CODES.containsKey(register)) {
            return REGISTER_CODES.get(register);
        } else {
            throw new IllegalArgumentException("Registrador desconhecido: " + register);
        }
    }

    // Método para converter um valor imediato para binário
    private static String immediateBinary(String value, int bits) {
        int number = Integer.parseInt(value);
        String binary;
        if (number < 0) {
            // Converte para complemento de dois
            binary = Integer.toBinaryString((1 << bits) + number);
        } else {
            binary = Integer.toBinaryString(number);
        }
        if (binary.length() > bits) {
            return binary.substring(binary.length() - bits);
        } else {
            return String.format("%" + bits + "s", binary).replace(' ', '0');
        }
    }
}
