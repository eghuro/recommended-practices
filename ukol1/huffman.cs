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
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.frequency = NodeHelper.SumFrequencies(leftChild, rightChild);
            this.symbol = leftChild.GetSymbol();
        }

        public int GetFrequency()
        {
            return frequency;
        }

        public byte GetSymbol()
        {
            return symbol;
        }

        public Node GetLeftChild()
        {
            return leftChild;
        }
        public Node GetRightChild()
        {
            return rightChild;
        }
        /// <summary>
        /// Kdyz nema jedineho syna vraci true.
        /// </summary>
        /// <returns></returns>
        public bool IsLeaf()
        {
            return ((leftChild == null) && (rightChild == null));
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

    class NodeHelper
    {
        public static int SumFrequencies(Node firstNode, Node secondNode)
        {
            return firstNode.GetFrequency() + secondNode.GetFrequency();
        }
    }

    class Tree
    {
        private const byte SpaceAsciiCode = 32;
        private const byte TildeAsciiCode = 126;

        private const string FrequencySeparator = " -+- ";
        private const string LineSeparator = "  |  ";
        private const string LeftChildSeparator = "  `- ";
        private const int MaxFrequencyLength = 4;

        private string source;
        private Node root = null;

        public Tree(string source)
        {
            this.source = source;
            Dictionary<byte, int> frequencies = CreateFrequenciesFromSource();
            List<Node> nodes = CreateFrequencyNodes(frequencies);
            BuildIt(nodes);
        }

        private Dictionary<byte, int> CreateFrequenciesFromSource()
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

        private List<Node> CreateFrequencyNodes(Dictionary<byte, int> frequencies)
        {
            List<Node> nodes = new List<Node>();

            foreach (KeyValuePair<byte, int> symbol in frequencies)
            {
                nodes.Add(new Node(symbol.Key, symbol.Value));
            }

            return nodes;
        }

        private void BuildIt(List<Node> nodes)
        {
            while (nodes.Count > 1)
            {
                List<Node> orderedNodes = nodes.OrderBy(node => node.GetFrequency())
                                                .ThenBy(node => node.GetSymbol())
                                                .ToList<Node>();

                if (orderedNodes.Count >= 2)
                {
                    List<Node> firstTwoOrderedNodes = orderedNodes.Take(2).ToList<Node>();
                    Node parent = new Node(firstTwoOrderedNodes[0], firstTwoOrderedNodes[1]);

                    nodes.Remove(firstTwoOrderedNodes[0]);
                    nodes.Remove(firstTwoOrderedNodes[1]);
                    nodes.Add(parent);
                }
            }

            this.root = nodes.FirstOrDefault();
        }

        public void Print()
        {
            if (this.root != null)
            {
                PrintRecursive(this.root, "");
            }
        }

        private void PrintRecursive(Node node, string outputPrefix)
        {
            if (node.IsLeaf())
            {
                PrintLeaf(node);
            }
            else
            {
                Console.Write("{0," + MaxFrequencyLength + "}{1}", node.GetFrequency(), FrequencySeparator);

                outputPrefix = IncreasePrefix(outputPrefix);

                PrintRecursive(node.GetRightChild(), GetRightChildPrefix(outputPrefix));

                PrintLineSeparator(outputPrefix);

                PrintRecursive(node.GetLeftChild(), GetLeftChildPrefix(outputPrefix));
            }
        }

        private string IncreasePrefix(string outputPrefix)
        {
            string space = new string(' ', MaxFrequencyLength);
            return outputPrefix + space;

        }

        private string GetRightChildPrefix(string outputPrefix)
        {
            return outputPrefix + LineSeparator;
        }

        private string GetLeftChildPrefix(string outputPrefix)
        {
            string space = new String(' ', MaxFrequencyLength + 1);
            return outputPrefix + space;
        }

        private void PrintLineSeparator(string outputPrefix)
        {
            Console.WriteLine(outputPrefix + LineSeparator);
            Console.Write(outputPrefix + LeftChildSeparator);
        }

        private void PrintLeaf(Node node)
        {
            if (IsPrintable(node.GetSymbol()))
            {
                Console.WriteLine(" ['{0}':{1}]", (char)node.GetSymbol(), node.GetFrequency());
            }
            else
            {
                Console.WriteLine(" [{0}:{1}]", node.GetSymbol(), node.GetFrequency());
            }
        }

        private bool IsPrintable(byte charAsciiCode)
        {
            return ((charAsciiCode >= SpaceAsciiCode) && (charAsciiCode <= TildeAsciiCode));
        }
    }

    class Loader
    {
        public static string SourceFromFile(string fileName)
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

            string source = Loader.SourceFromFile(args[0]);

            if (source.Length > 0)
            {
                Tree huffmanTree = new Tree(source);
                Console.WriteLine();
                huffmanTree.Print();
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
