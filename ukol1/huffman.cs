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

        public Node(byte symbol, int frequency)
        {            
            this.symbol = symbol;
            this.frequency = frequency;
        }

        public Node(Node leftChild, Node rightChild)
        {
            this.frequency = leftChild.frequency + rightChild.frequency;
            this.symbol = leftChild.symbol;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
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
        private Node root = null;

        public Tree(String source)
        {
            process(source);
        }

        public void process(String source)
        {
            Dictionary<byte, int> frequencies = createFrequenciesFromSource(source);
            List<Node> nodes = createFrequencyNodes(frequencies);
            build(nodes);
        }

        public Dictionary<byte, int> createFrequenciesFromSource(String source)
        {
            Dictionary<byte, int> frequencies = new Dictionary<byte, int>();

            for (int i = 0; i < source.Length; i++)
            {
                byte symbol = (byte)source[i];

                if (!frequencies.ContainsKey(symbol))
                {
                    frequencies.Add(symbol, 0);
                }

                frequencies[symbol]++;
            }

            return frequencies;
        }


        public List<Node> createFrequencyNodes(Dictionary<byte, int> frequencies)
        {            
            List<Node> nodes = new List<Node>();

            foreach (KeyValuePair<byte, int> symbol in frequencies)
            {
                nodes.Add(new Node(symbol.Key, symbol.Value));
            }

            return nodes;
        }

        private void build(List<Node> nodes)
        {
            while (nodes.Count > 1)
            {
                List<Node> orderedNodes = nodes.OrderBy(node => node.frequency).ThenBy(node => node.symbol).ToList<Node>();

                if (orderedNodes.Count >= 2)
                {
                    List<Node> taken = orderedNodes.Take(2).ToList<Node>();
                    Node parent = new Node(taken[0], taken[1]);

                    nodes.Remove(taken[0]);
                    nodes.Remove(taken[1]);
                    nodes.Add(parent);
                }
            }

            this.root = nodes.FirstOrDefault();
        }

        public void print()
        {
            if (this.root != null)
            {
                VypisStrom2(this.root, "");
            }
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

    class Loader
    {

        public static String sourceFromFile(string fileName)
        {
            String source = "";

            try
            {
                using (StreamReader sr = new StreamReader(fileName))
                {
                    source = sr.ReadToEnd();
                }
            }
            catch (Exception e)
            {
                Console.Write("File Error");
                Environment.Exit(0);
            }

            return source;
        }

    }

    class Program
    {        
        //   static Stopwatch sw = new Stopwatch();

        static void Main(string[] args)
        {            
            Tree huffmanTree;
            //     sw.Start();

            if (args.Length != 1)
            {
                Console.Write("Argument Error");
                Environment.Exit(0);
            }

            String source = Loader.sourceFromFile(args[0]);

            if (source.Length > 0)
            {
                huffmanTree = new Tree(source);
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
