import java.util.ArrayList;

public class DNA {
    public static void main(String[] args) {
        String DNA1 = "CTGATATTGTATCCGGCCGAT";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAC";

        ArrayList<String> aminoAcids1 = dnaToAminoAcids(DNA1);
        ArrayList<String> aminoAcids2 = dnaToAminoAcids(DNA2);
        ArrayList<String> aminoAcids3 = dnaToAminoAcids(DNA3);
        boolean match12 = isMatch(aminoAcids1, aminoAcids2);
System.out.println("DNA1 and DNA2 match: " + match12);

        boolean match13 = isMatch(aminoAcids1, aminoAcids3);
System.out.println("DNA1 and DNA3 match: " + match13);

boolean match23 = isMatch(aminoAcids2, aminoAcids3);
System.out.println("DNA2 and DNA3 match: " + match23);
    }

    public static ArrayList<String> dnaToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<String>();
        for (int i = 0; i < dna.length(); i += 3) {
            String codon = dna.substring(i, i + 3);
            codons.add(codon);
        }
        return codons;
    }

    public static String codonToAminoAcid(String codon) {
        if (codon.equals("TTT") || codon.equals("TTC")) {
            return "F";
        } else if (codon.equals("TTA") || codon.equals("TTG") || codon.equals("CTT") || codon.equals("CTC") || codon.equals("CTA") || codon.equals("CTG")) {
            return "L";
        } else if (codon.equals("ATT") || codon.equals("ATC") || codon.equals("ATA")) {
            return "I";
        } else if (codon.equals("ATG")) {
            return "M";
        } else if (codon.equals("GTT") || codon.equals("GTC") || codon.equals("GTA") || codon.equals("GTG")) {
            return "V";
        } else if (codon.equals("TCT") || codon.equals("TCC") || codon.equals("TCA") || codon.equals("TCG") || codon.equals("AGT") || codon.equals("AGC")) {
            return "S";
        } else if (codon.equals("CCT") || codon.equals("CCC") || codon.equals("CCA") || codon.equals("CCG")) {
            return "P";
        } else if (codon.equals("ACT") || codon.equals("ACC") || codon.equals("ACA") || codon.equals("ACG")) {
            return "T";
        } else if (codon.equals("GCT") || codon.equals("GCC") || codon.equals("GCA") || codon.equals("GCG")) {
            return "A";
        } else if (codon.equals("TAT") || codon.equals("TAC")) {
            return "Y";
        } else if (codon.equals("TAA") || codon.equals("TAG") || codon.equals("TGA")) {
            return "Stop";
        } else if (codon.equals("CAT") || codon.equals("CAC")) {
            return "H";
        } else if (codon.equals("CAA") || codon.equals("CAG")) {
            return "Q";
        } else if (codon.equals("AAT") || codon.equals("AAC")) {
            return "N";
        } else if (codon.equals("AAA") || codon.equals("AAG")) {
            return "K";
        } else if (codon.equals("GAT") || codon.equals("GAC")) {
            return "D";
        } else if (codon.equals("GAA") || codon.equals("GAG")) {
            return "E";
        } else if (codon.equals("TGT") || codon.equals("TGC")) {
            return "C";
        } else if (codon.equals("TGG")) {
            return "W";
        } else if (codon.equals("CGT") || codon.equals("CGC") || codon.equals("CGA") || codon.equals("CGG") || codon.equals("AGA") || codon.equals("AGG")) {
            return "R";
        } else if (codon.equals("GGT") || codon.equals("GGC") || codon.equals("GGA") || codon.equals("GGG")) {
            return "G";
        }
        return "?";
    }

    public static ArrayList<String> dnaToAminoAcids(String dna) {
        ArrayList<String> codons = dnaToCodons(dna);
        ArrayList<String> aminoAcids = new ArrayList<String>();
        for (int i = 0; i < codons.size(); i++) {
            String codon = codons.get(i);
            String aminoAcid = codonToAminoAcid(codon);
            aminoAcids.add(aminoAcid);
        }
        return aminoAcids;
    }

    public static boolean isMatch(ArrayList<String> aminoSeq1, ArrayList<String> aminoSeq2) {
        if (aminoSeq1.size() != aminoSeq2.size()) {
            return false;
        }
        for (int i = 0; i < aminoSeq1.size(); i++) {
            if (!aminoSeq1.get(i).equals(aminoSeq2.get(i))) {
                return false;
            }
        }
        return true;
    }
}