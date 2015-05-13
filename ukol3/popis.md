Funkční požadavky a popis použití
====
Volby se specifikují ve třídě CommandLine pomocí metody addOption. Ta bere jako
argument instanci třídy Option. 

Option se vytváří pomocí třídy OptionBuilder, ve které se doplní specifikace
volby - jméno a synonyma, zda se jedná o povinnou volbu, nastavit typ parametru
volby a doplnit slovní popis.

Parametry jsou vždy odvozeny od abstraktní třídy Argument. K dispozici jsou
třídy pro parametry typu Boolean, Enum, Integer a String. Parametry mají vždy
jméno, lze specifikovat, že je parametr povinný či implicitní hodnotu.

Pro každý typ parametru je k dispozici Builder, který má na starosti vytvoření
příslušné instance.

Typ volby (krátká nebo dlouhá) rozlišujeme podle jejich délky. Pokud uživatel
zadá volbu "v", je brána automaticky jako krátká a bude se vyžadovat přepínač 
"-v", zatímco volba "verbose" bude brána jako dlouhá volba a bude se vyžadovat
"--verbose" jako parametr.

Pro vlastní parsování se používá třída CommandLineParser a metodou parse načte
vstup z příkazové řádky. Při načítání dojde i k validaci a pokud není splněno
některé nastavené omezení je vyhozena výjimka.

Následné zpracování je ponecháno uživateli. Ten má možnost se zeptat, zda byla
nějaká volba zadána, zjistit její případnou hodnotu a nechat si vypsat obyčejné
argumenty.

Na třídě CommandLine lze zavolat metodu pro vypsání dokumentace nastavených 
voleb.

Hlubší pohled na architekturu
====
Základní třídy jsou CommandLine a CommandLineParser. Úlohou třídy CommandLine
je schraňovat konfiguraci přijímaných voleb. Úkolem třídy CommandLineParser je
parsovat argumenty příkazové řádky a umožnit nad nimi dotazování.

Pro definici volby je použita třída Option, pro definici argumentu abstraktní 
třída Argument s odvozenými třídami dle konkrétního typu. Tyto jsou vytvářeny
s pomocí návrhového vzoru Builder jak je popsáno výše. Pro procházení voleb
se používá návrhový vzor Visitor. Definovali jsme Visitory pro různé používané
aktivity.

Knihovnu lze snadno rozšířit přidáním nového typu argumentu vytvořením nové,
která dědí od abstaktní třídy Argument. Funkcionalitu nad načtenou konfigurací
nebo naparsovanou příkazovou řádkou lze doplnit naimplementováním dalšího 
Visitoru.

Zdůvodnění návrhu
===
První rozhodnutí při návrhu bylo použití návrhového vzoru Builder. Důvodem pro
jeho použití bylo velké množství různých kombinací nastavení, což je modelový
případ použití tohoto vzoru. Při implementaci jsme zvažovali několik variant.

Jedna varianta byla použití Builderu v Javě typickým způsobem pomocí vnořené
třídy. Tuto jsme zavrhli, protože nám nepřišlo hezké volání typu new Option.
OptionBuilder z hlediska uživatelského komfortu a také zanořování tříd s 
podobným, avšak přesto různým úkolem

Z tohoto důvodu jsme přešli k druhé variantě - buildery jsou vždy v kompletně
oddělené třídě a pomocí metody create() následně vytvoří nový objekt. Zkoušeli
jsme použítí statických metod, ale nakonec jsme vybrali cestu vytvoření nového
objektu Builder.

Další rozhodnutí bylo použití návrhového vzoru Visitor pro vykonávání různých
činností nad předanými objekty namísto složitého algoritmu zakomponovaného na
jednom místě - typicky ve třídě CommandLine či CommandLineParser, který by
zásadním způsobem zkomplikoval rozšiřitelnost knihovny a byl celkově méně
čitelný. Nyní máme definované Visitory pro výpis voleb, výpis použití knihovny,
validaci a hledání parametru podle jména.

Pokud má být knihovna uživatelsky použitelná, rozhodli jsme se ponechat 
uživateli prostor pro využití dat bez zvláštních omezení. Uživatel má možnost
se zeptat, zda je nastavena některá volba, může získat hodnotu argumentu a
může získat seznam obyčejných argumentů.

Testy
===
Definujeme sadu 43 testů k otestování funkcionality knihovny. U testů, které 
očekávají výjimku jsme doplnili dokumentaci se zdůvodněním, v ostatních
případech je z assertů poznat, co je testováno.

Testy se spouštějí pomocí TestSuite AllTests.

Připomínky od oponentů
===
Bylo nám vytčeno nedotažení návrhového vzoru Builder. Jednotlivé implementace
jsme vylepšili. 

Opravili jsme typy parametrů ve fukncích setMin/MaxValue/Length.

Neopravovali jsme Argument::min/maxListValue, jelikož Java nemá bezznaménkové
typy. 

Výtku o předávání vícenásobných hodnot pomocí řetězce jsme nepochopili.

API třídy Argument jsme změnili tak, aby se veškerá omezení nastavovala v 
odvozených třídách. 

Jelikož se v Javě nedají předávat funkce jako parametry
rozhodli jsme se pouze ponechat možnost specifikovat jistá integritní omezení
na jednotlivých argumentech, avšak již zásadním způsobem neměnit způsob 
omezení hodnot.
