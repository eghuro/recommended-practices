using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace HuffmanskeKapky
{
//NOTE: pro odsazovani pouzity 4 mezery - dle MS specifikace
    class Vrchol: IComparable<Vrchol>
    {
	// NOTE: promenne jsou verejne, lepsi je mit soukrome a pripadne poskytnout API pomoci setter/getter metod (nebo C# properties)
        public Vrchol Psyn;        // NOTE: mezery na konci radku
        public int vaha;
        public byte znak;
        public Vrchol Lsyn;  // NOTE: mozna lepsi spojit promenne typu vrchol na jeden radek
        
        int stari;

        static int cisloVrchola;

        public Vrchol(int vaha, byte znak, Vrchol Lsyn, Vrchol Psyn)
        {
            this.vaha = vaha;
            this.znak = znak;
            this.Lsyn = Lsyn;
            this.Psyn = Psyn;
            stari = cisloVrchola; // NOTE: cislo vrchola neni na zacatku inicializovano, lepsi je v definici rovnou dosadit 0 ci 1
            cisloVrchola++;  // NOTE: cisloVrchola staticka polozka, lepsi pouzit vrchol.cisloVrchola
        }

        /// <summary>
        /// Kdyz nema jedineho syna vraci true.
        /// </summary>
        /// <returns></returns>
        public bool JeList()
        {
            if ((Lsyn == null) && (Psyn == null))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public static int SectiVahy(Vrchol prvni, Vrchol druhy)
        {
            return prvni.vaha + druhy.vaha;  // NOTE: vrchol je objekt, neni testovano, zda neni nahodou NULL
        }

        /// <summary>
        /// Zvetsi vahu vrcholu o zadany int, vraci upraveny vrchol.
        /// </summary>
        /// <param name="rank"></param>
        /// <returns></returns>
        public Vrchol ZvecVahu(int rank)
        {
            vaha += rank;
            return this;
        }

        /// <summary>
        /// True o sobe vrchol rekne jestli bude v Huffmanskem strome nalevo od druheho vrcholu.
        /// </summary>
        /// <param name="druhy"></param>
        /// <returns></returns>
        public bool BudeVrcholVlevo(Vrchol druhy)
        {  // NOTE: opet netestujeme parametr
           // NOTE: ocenil bych komentare, abych nemusel premyslet, co se tu deje a zda je to spravne
	   // NOTE: rozbit do nekolika boolu a neopakovat dve stejne podminky v ruznych kombinacich ...?
            if (druhy.vaha > vaha)
            {
                return true;
            }
            else if (druhy.vaha < vaha)
            {
                return false;
            }
            else if (druhy.JeList() && !(JeList()))
            {
                return false;
            }
            else if (JeList() && !(druhy.JeList()))
            {
                return true;
            }
            else if ((JeList()) && (druhy.JeList()) && (znak < druhy.znak))
            {
                return true;
            }
            else if ((JeList()) && (druhy.JeList()) && (znak > druhy.znak))
            {
                return false;
            }
            else if (stari < druhy.stari)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        #region IComparable Members

        public int CompareTo(Vrchol obj)  // NOTE: neni od veci popsat - co to je #region, proc 0, 1, -1
        {
            if (this == obj)
            {
                return 0;
            }
            else if (BudeVrcholVlevo(obj))
            {
                return -1;
            }
            else 
            {
                return 1;
            }
            
        }

        #endregion
    }

    class Strom
    {
        private Vrchol koren;  // NOTE: neni konsistentni - jinde soukrome promenne nejsou oznaceny private

        public Strom(SortedDictionary<int, List<Vrchol>> vrcholy)
        {
            postavStrom(vrcholy);
		// NOTE: neni zjevne, zda se inicializuje promenna koren
        }

        int pocetStromu = 0;  // NOTE: pouziva se pouze v postavStrom, ma byt lokalni

        private void postavStrom(SortedDictionary<int, List<Vrchol>> HuffmanskyLes)  // NOTE: metoda je sice privatni, ale dela nejake netrivialni operace - potreba komentare, co se zde deje
        {
            List<vrchol> seznam;  // NOTE: spatne odsazeni
                Vrchol pom1;  // NOTE: mozna zvolit lepsi jmena vrcholu, aby v new vrchol nize bylo zjevne, co to je; proc pom1 a pom3, kde je pom2
                Vrchol pom3;
                Vrchol novy;
                Vrchol lichy = null;  // NOTE: pripsal bych nazvum promennych suffix oznacujici, ze jde o vrchol
                int ZbyvaZpracovat = 0;  // NOTE: ZbyvaZpracovat bude vyvolavat zdani promenne tridy nebo jine globalnejsi promenne - nikoliv lokalni, navic nekonsistentni se zbytkem
                int rank;
		// NOTE: zde potrebuji jen ZbyvaZpracovat

                foreach (KeyValuePair<int,List<Vrchol>> item in HuffmanskyLes)  // NOTE: chybi mezera za int,
                {
                    ZbyvaZpracovat += item.Value.Count;
                }

		//NOTE: pocetStromu potreba az zde
            if (ZbyvaZpracovat != 1) {
                pocetStromu = pocetStromu + 1;
            }

		// NOTE: lichy potrebuji az zde
            while (ZbyvaZpracovat != 1)  // NOTE: v tele cyklu netrivialni kod - potreba komentaru, co se tu vlastne deje
            {
		//seznam a rank potrebuji az zde
                seznam = HuffmanskyLes[HuffmanskyLes.Keys.ElementAt(0)];
                rank = HuffmanskyLes.Keys.ElementAt(0);

                if (lichy == null)
                {
                    for (int i = 0; i < seznam.Count - 1; i++)
                    {  // NOTE: tento kod se dale opakuje, je zahodno jej vlozit do vlastni metody
			// pom1, pom3->pom2, novy potrebuji az zde resp. v pomocne metode
                        pom1 = seznam[i];
                        pom3 = seznam[++i];

                        if (pom1.BudeVrcholVlevo(pom3))
                        {
                            novy = new Vrchol(pom1.vaha + pom3.vaha, pom1.znak, pom1, pom3);
                        }
                        else novy = new Vrchol(pom1.vaha + pom3.vaha, pom1.znak, pom3, pom1);

                        if (HuffmanskyLes.ContainsKey(novy.vaha))
                        {
                            HuffmanskyLes[novy.vaha].Add(novy);
                        }
                        else HuffmanskyLes.Add(novy.vaha, new List<Vrchol>() { novy });
                        
                        
                        ZbyvaZpracovat--;
                    }
                    if (seznam.Count % 2 == 1)
                    {
                        lichy = seznam[seznam.Count - 1];

                    }
                    else
                    {
                        lichy = null;
                    }

                }
                else 
                {
                    pom1 = seznam[0];
			// NOTE: strida se pouziti jednoradkoveho tela if-else v {} a bez {}
                    if (lichy.BudeVrcholVlevo(pom1))
                    {
                        novy = new Vrchol(lichy.vaha + pom1.vaha, lichy.znak, lichy, pom1);
                    }
                    else novy = new Vrchol(pom1.vaha + lichy.vaha, pom1.znak, pom1, lichy);

                    if (HuffmanskyLes.ContainsKey(novy.vaha))
                    {
                        HuffmanskyLes[novy.vaha].Add(novy);
                    }
                    else HuffmanskyLes.Add(novy.vaha, new List<Vrchol>() { novy });

                    ZbyvaZpracovat--;

                    for (int i = 1; i < seznam.Count - 1; i++)
                    {
                        pom1 = seznam[i];
                        pom3 = seznam[++i];

                        if (pom1.BudeVrcholVlevo(pom3))
                        {
                            novy = new Vrchol(pom1.vaha + pom3.vaha, pom1.znak, pom1, pom3);
                        }
                        else novy = new Vrchol(pom1.vaha + pom3.vaha, pom1.znak, pom3, pom1);

                        if (HuffmanskyLes.ContainsKey(novy.vaha))
                        {
                            HuffmanskyLes[novy.vaha].Add(novy);
                        }
                        else HuffmanskyLes.Add(novy.vaha, new List<Vrchol>() { novy });

                        ZbyvaZpracovat--;
                    }
                    if (seznam.Count % 2 == 0)
                    {
                        lichy = seznam[seznam.Count - 1];
                    }
                    else lichy = null;
                }
                HuffmanskyLes.Remove(rank);
            }
            koren = HuffmanskyLes[HuffmanskyLes.Keys.ElementAt(0)][0];  // NOTE: komentar proc takto, mozna je lepsi dat tento prikaz do konstruktoru
        }
       
        public void VypisStrom()  // NOTE: verejna metoda bez komentare, ktera nic nedela
        {
            // VypisStrom(this.koren);
        }

        public void VypisStrom2()  // NOTE: verejna metoda bez komentare
        {
            VypisStrom2(this.koren, "");
        }
        
        public void VypisStrom2(Vrchol vrch, string pre)  // NOTE: verejna metoda bez komentare, navic popsat parametry neni od veci - pre je asi prefix, ale kdo to ma vedet, popsat tvar vypisu atd.
        {
            bool bylVlevo = false;  // NOTE: presunout pod if

            if (vrch.JeList()) {
                if ((vrch.znak >= 32) && (vrch.znak <= 0x7E))  // NOTE: zadratovane konstanty, nekonsistence mezi desitkovym a sesnactkovym zapisem
                {
                    Console.Write(" ['{0}':{1}]\n", (char) vrch.znak, vrch.vaha);
                    return; // NOTE: toto resi return pod else {}
                }
                else
                {
                    Console.Write(" [{0}:{1}]\n", vrch.znak, vrch.vaha);
                }
                return;
            }
            else
            {
                // bylVlevo = true;
            }
                
	    // NOTE: toto je provede vzdy kdyz by se spadlo do else vetve - proto by to melo vyt v else vetvi a odpustime si return v if - prehlednost
            if (!bylVlevo)
            {
                Console.Write("{0,4} -+- ", vrch.vaha);
                bylVlevo = true;
            }
            pre = pre + "      ";
            if (bylVlevo)  // NOTE: jsou pouzity dva if-y, vyvolava se zdani, ze se jde do jedne nebo druhe sekce, avsak pri pozornem cteni si vsimneme, ze se vzdy oba nepodminene provedou :(
            {
                VypisStrom2(vrch.Psyn, pre + "|  ");
                Console.Write("{0}|\n", pre);
                Console.Write("{0}`- ", pre);
                VypisStrom2(vrch.Lsyn, pre + "   ");
            }
        }
    }

    class Nacitacka  // NOTE: najednou je jmeno tridy s velkym pismenem
    {
        private static FileStream vstup;

        public static bool OtevrSoubor(string nazev)  // NOTE: metoda by mohla byt void - vzdy vrati true, verejna metoda bez komentare (ikdyz je zhruba jasne, co dela)
        {
            try
            {
                vstup = new FileStream(nazev, FileMode.Open, FileAccess.Read);  // NOTE: alespon Nacitacka.vstup, pouvazovat nad potrebou staticke promenne
                if (!(vstup.CanRead))  // NOTE: nacitacka.vstup
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

        public static SortedDictionary<int, List<Vrchol>> PrectiSoubor(string nazev)  // NOTE: verejna metoda bez komentare (ikdyz je zhruba jasne, co dela)
        {

            if (!(OtevrSoubor(nazev))) return null;  // NOTE: vlastne zbytecne, jelikoz OtevrSoubor vzdy vrati true
            else
            {
		// NOTE: lze pochopit, co se zhruba deje, ale komentar, co delaji jednotlive cykly neni na skodu
                SortedDictionary<int, List<Vrchol>> vrcholy = new SortedDictionary<int, List<Vrchol>>();
                byte a = 0;
             
                Vrchol[] prvky = new Vrchol[256];  // NOTE: zadratovana konstanta
                byte[] bafr = new byte[0x4000];  // NOTE: zadratovana konstanta

                for (int i = 0; i < vstup.Length / 0x4000; i++)  // NOTE: zadratovana konstanta, mozna uzavorkovat vyraz, aby bylo jasne, ze se chova dle autorova zameru; vstup -> Nacitacka.vstup
                {
                    vstup.Read(bafr, 0, 16384);  // NOTE: zadratovana konstanta; vstup -> Nacitacka.vstup

                    for (int j = 0; j < 16384; j++)  // NOTE: zadratovana konstanta
                    {
                        a = bafr[j];
                        if (prvky[a] == null)
                        {
                            prvky[a] = new Vrchol(1, (byte)a, null, null);
                            //   vrcholy.Add(prvky[a]);
                        }
                        else
                        {
                            prvky[a].vaha++;
                        }
                    }
                }

                for (int i = 0; i < vstup.Length % 0x4000; i++)  // NOTE: zadratovana konstanta; vstup -> Nacitacka.vstup
                {
                    a =(byte) vstup.ReadByte();  // NOTE: co kdyz nacteme znak mimo rozsah pole?
                    if (prvky[a] == null)
                    {
                        prvky[a] = new Vrchol(1, (byte)a, null, null);  // NOTE: cast z byte do byte
                        //   vrcholy.Add(prvky[a]);
                    }
                    else
                    {
                        prvky[a].vaha++;
                    }
                }

                for (int i = 0; i < 256; i++)  // NOTE: zadratovana konstanta
                {
                    if (prvky[i]!= null)  // NOTE: chybi mezera pred !=
	                {  // NOTE: nekonsistentni odsazeni
                        if (vrcholy.ContainsKey(prvky[i].vaha))
                        {
                            vrcholy[prvky[i].vaha].Add(prvky[i]);
                    }
                    else vrcholy.Add(prvky[i].vaha, new List<Vrchol>() { prvky[i] });
                    }  // NOTE: hodne spatne odsazeni neni na prvni pohled vubec jasne, jaka } se vaze ke ktere {
                }
                foreach (KeyValuePair<int,List<Vrchol>> item in vrcholy)
                {
                    item.Value.Sort();
                }
                return vrcholy;
            }
        }

    }

    class Program  // NOTE: nebo program? nebo Vrchol, Strom
    {
	// NOTE: promenne maji byl lokalni v Main
        static SortedDictionary<int, List<Vrchol>> vrcholy;
        static strom Huffman;  // NOTE: jmeno promenne zacina velkym pismenem
     //   static Stopwatch sw = new Stopwatch();

        static void Main(string[] args)  // NOTE: mnoho zakomentovaneho kodu, co tu dela? - vyhodit nebo napsat komentar
        {
       //     sw.Start();

            if (args.Length != 1)
            {
                Console.Write("Argument Error");
                Environment.Exit(0);
            }
            vrcholy = Nacitacka.PrectiSoubor(args[0]);


            if ((vrcholy != null) && (vrcholy.Count != 0))
            {
                Huffman = new strom(vrcholy);
                Huffman.VypisStrom();
                //Console.Write("\n");
                Huffman.VypisStrom2();
                Console.Write("\n");
            }

      /*      sw.Stop();
            string ExecutionTimeTaken = string.Format("Minutes :{0}\nSeconds :{1}\n Mili seconds :{2}", sw.Elapsed.Minutes, sw.Elapsed.Seconds, sw.Elapsed.TotalMilliseconds);
            Console.Write(ExecutionTimeTaken);
            Console.ReadKey();

            Console.ReadKey(); */
        }
    }
}
