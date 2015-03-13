using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace Huffman
{
    class NodeHelper
    {
        public static int SumWeight(Node firstNode, Node secondNode) // NOTE: nepouziva se
        {
            return firstNode.GetFrequency() + secondNode.GetFrequency();
        }
    }

    class Node
    {
        private Node leftChild = null;
        private Node rightChild = null;
        private int frequency;
        private byte symbol;

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

        public int GetFrequency()
        {
            return frequency;
        }

        public byte GetSymbol()
        {
            return symbol;
        }

        public Node GetRightChild()
        {
            return rightChild;
        }

        public Node GetLeftChild()
        {
            return leftChild;
        }

        /// <summary>
        /// Kdyz nema jedineho syna vraci true.
        /// </summary>
        /// <returns></returns>
        public bool IsLeaf()
        {
            return (leftChild == null) && (rightChild == null);
        }

        /// <summary  >
        /// Zvetsi vahu vrcholu o zadany int, vraci upraveny Node.
        /// </summary>
        /// <param name="frequency"></param>
        /// <returns></returns>
        public Node IncreaseFrequency(int frequency) // NOTE: nepouziva se
        {
            this.frequency += frequency;
            return this;
        }
    }

    class Tree
    {
        private Node root = null;

        public Tree(string source)
        {
            Dictionary<byte, int> frequencies = createFrequenciesFromSource(source);
            List<Node> nodes = createFrequencyNodes(frequencies);
            buildIt(nodes); // TODO: divny nazev - spatne se cte - buildIt nodes? alternativy buildTree? this.buildIt? ...
        }

        private Dictionary<byte, int> createFrequenciesFromSource(string source)
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

        private List<Node> createFrequencyNodes(Dictionary<byte, int> frequencies)
        {
            List<Node> nodes = new List<Node>();

            foreach (KeyValuePair<byte, int> symbol in frequencies)
            {
                nodes.Add(new Node(symbol.Key, symbol.Value));
            }

            return nodes;
        }

        private void buildIt(List<Node> nodes)
        {
            while (nodes.Count > 1)
            {
                List<Node> orderedNodes = nodes.OrderBy(node => node.GetFrequency()).ThenBy(node => node.GetSymbol()).ToList<Node>(); // TODO: moc dlouhe ...

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
                print(this.root, "");
            }
        }

        private void print(Node node, string pre)
        {
            if (node.IsLeaf())
            {
                printLeaf(node);
                //TODO: printLeaf nepouziva pre, je to korektni? nema printInner volat opet printInner? ...
            }
            else
            {
                printInner(node, pre);
            }
        }

        private void printLeaf(Node node)
        {
            // TODO: rozdil mezi vetvemi je minimalni, co to dela?
            if (isPrintable(node.GetSymbol()))
            {
                Console.WriteLine(" ['{0}':{1}]", (char)node.GetSymbol(), node.GetFrequency());
            }
            else
            {
                Console.WriteLine(" [{0}:{1}]", node.GetSymbol(), node.GetFrequency());
            }
        }

        private bool isPrintable(byte b)
        {
            // v ASCII 32 (mezera) az 126 (vlnka) jsou tisknutelne znaky
            const byte begin = 32;  // mezera
            const byte end = 126;  // vlnka
            return ((b >= begin) && (b <= end));
        }

        private void printInner(Node node, string pre)
        {
            //TODO: nejak opravit hardcoded konstanty
            Console.Write("{0,4} -+- ", node.GetFrequency());

            pre = pre + "      ";
            print(node.GetRightChild(), pre + "|  ");
            Console.WriteLine("{0}|", pre);
            Console.Write("{0}`- ", pre);
            print(node.GetLeftChild(), pre + "   ");
        }
    }

    class Loader
    {
        public static string sourceFromFile(string fileName)
        {
            string source = "";

            try
            {
                using (StreamReader sr = new StreamReader(fileName))
                {
                    source = sr.ReadToEnd();
                }
            }
            catch (Exception e)
            {
                Program.Error("File Error");
            }

            return source;
        }

    }

    class Program
    {
        //   static Stopwatch sw = new Stopwatch();

        public static void Error(string message)
        {
            Console.Write(message);
            Environment.Exit(0);
        }        

        static void Main(string[] args)
        {
            //     sw.Start();

            if (args.Length != 1)
            {
                Program.Error("Argument Error");
            }

            string source = Loader.sourceFromFile(args[0]);

            if (source.Length > 0)
            {
                Tree huffmanTree = new Tree(source);
                // TODO: print se vola jen zde - doplnit Console.Write("\n") do print nebo nasledujici radky do private metody Program
                Console.WriteLine();
                huffmanTree.print();
                Console.WriteLine();
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
