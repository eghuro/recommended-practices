using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace Huffman
{

    class Node
    {
        public Node leftChild = null;
        public Node rightChild = null;
        public int frequency;
        public byte symbol;
        int stari;

        static int cisloVrchola = 0;

        public Node(byte symbol, int frequency)
        {            
            this.symbol = symbol;
            this.frequency = frequency;
            stari = cisloVrchola;
            cisloVrchola++;
        }

        public Node(Node leftChild, Node rightChild)
        {
            this.frequency = leftChild.frequency + rightChild.frequency;
            this.symbol = leftChild.symbol;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            stari = cisloVrchola;
            cisloVrchola++;
        }

        /// <summary>
        /// Kdyz nema jedineho syna vraci true.
        /// </summary>
        /// <returns></returns>
        public bool isLeaf()
        {
            return (leftChild == null && rightChild == null);
        }

        public static int SectiVahy(Node prvni, Node druhy)
        {
            return prvni.frequency + druhy.frequency;
        }

        /// <summary>
        /// Zvetsi vahu vrcholu o zadany int, vraci upraveny Node.
        /// </summary>
        /// <param name="frequency"></param>
        /// <returns></returns>
        public Node increaseFrequency(int frequency)
        {
            this.frequency += frequency;
            return this;
        }
    }

    class Tree
    {
        private Node root;
        private List<Node> nodes = new List<Node>();

        public Tree(Dictionary<byte, int> frequencies)
        {
            foreach (KeyValuePair<byte, int> symbol in frequencies)
            {
                nodes.Add(new Node(symbol.Key, symbol.Value));
            }
            build();
        }

        private void build()
        {
            while (nodes.Count > 1)
            {
                List<Node> orderedNodes = nodes.OrderBy(node => node.frequency).ToList<Node>();

                if (orderedNodes.Count >= 2)
                {
                    List<Node> taken = orderedNodes.Take(2).ToList<Node>();
                    Node parent = new Node(taken[0], taken[1]);

                    nodes.Remove(taken[0]);
                    nodes.Remove(taken[1]);
                    nodes.Add(parent);
                }

                this.root = nodes.FirstOrDefault();

            }
            
        }


        public void print()
        {
            VypisStrom2(this.root, "");
        }

        public void VypisStrom2(Node node, string pre)
        {
            bool bylVlevo = false;

            if (node.isLeaf())
            {
                if ((node.symbol >= 32) && (node.symbol <= 0x7E))
                {
                    Console.Write(" ['{0}':{1}]\n", (char)node.symbol, node.frequency);
                    return;
                }
                else
                {
                    Console.Write(" [{0}:{1}]\n", node.symbol, node.frequency);
                }
                return;
            }
            else
            {
                // bylVlevo = true;
            }

            if (!bylVlevo)
            {
                Console.Write("{0,4} -+- ", node.frequency);
                bylVlevo = true;
            }
            pre = pre + "      ";
            if (bylVlevo)
            {
                VypisStrom2(node.rightChild, pre + "|  ");
                Console.Write("{0}|\n", pre);
                Console.Write("{0}`- ", pre);
                VypisStrom2(node.leftChild, pre + "   ");
            }
        }
    }

    class Nacitacka
    {
        private static FileStream input;

        public static bool OtevrSoubor(string nazev)
        {
            try
            {
                input = new FileStream(nazev, FileMode.Open, FileAccess.Read);
                if (!(input.CanRead))
                {
                    throw new Exception();
                }
            }
            catch (Exception)
            {
                Console.Write("File Error");
                Environment.Exit(0);
                //    return false;
            }
            return true;
        }

        public static Dictionary<byte, int> PrectiSoubor(string nazev)
        {

            if (!(OtevrSoubor(nazev))) return null;
            else
            {
                Dictionary<byte, int> frequencies = new Dictionary<byte, int>();
                byte a = 0;

                byte[] bafr = new byte[0x4000];

                for (int i = 0; i < input.Length / 0x4000; i++)
                {
                    input.Read(bafr, 0, 16384);

                    for (int j = 0; j < 16384; j++)
                    {
                        a = bafr[j];

                        if (!frequencies.ContainsKey((byte)a))
                        {
                            frequencies.Add((byte)a, 0);
                        }

                        frequencies[(byte)a]++;
                    }
                }

                for (int i = 0; i < input.Length % 0x4000; i++)
                {
                    a = (byte)input.ReadByte();

                    if (!frequencies.ContainsKey((byte)a))
                    {
                        frequencies.Add((byte)a, 0);
                    }

                    frequencies[(byte)a]++;
                }

                return frequencies;
            }
        }

    }

    class Program
    {        
        //   static Stopwatch sw = new Stopwatch();

        static void Main(string[] args)
        {
            Dictionary<byte, int> frequencies = new Dictionary<byte, int>();
            Tree huffmanTree;
            //     sw.Start();

            if (args.Length != 1)
            {
                Console.Write("Argument Error");
                Environment.Exit(0);
            }
            frequencies = Nacitacka.PrectiSoubor(args[0]);


            if ((frequencies != null) && (frequencies.Count != 0))
            {
                huffmanTree = new Tree(frequencies);
                Console.Write("\n");
                huffmanTree.print();
                Console.Write("\n");
            }

            /*      sw.Stop();
                  string ExecutionTimeTaken = string.Format("Minutes :{0}\nSeconds :{1}\n Mili seconds :{2}", sw.Elapsed.Minutes, sw.Elapsed.Seconds, sw.Elapsed.TotalMilliseconds);
                  Console.Write(ExecutionTimeTaken);
                  Console.ReadKey();
*/
                  Console.ReadKey(); 
        }
    }
}
