package tm;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;

import tm.card.AcquiredCompany;
import tm.card.AdaptationTechnology;
import tm.card.AdaptedLichen;
import tm.card.AdvancedAlloys;
import tm.card.AdvancedEcosystems;
import tm.card.Algae;
import tm.card.AntiGravityTechnology;
import tm.card.Ants;
import tm.card.Archaebacteria;
import tm.card.ArcticAlgae;
import tm.card.ArtificialLake;
import tm.card.ArtificialPhotosynthesis;
import tm.card.Asteroid;
import tm.card.AsteroidMining;
import tm.card.AsteroidMiningConsortium;
import tm.card.BeamFromThoriumAsteroid;
import tm.card.BigAsteroid;
import tm.card.Birds;
import tm.card.BlackPolarDust;
import tm.card.BreathingFilters;
import tm.card.BribedCommittee;
import tm.card.BuildingIndustries;
import tm.card.BusinessContracts;
import tm.card.BusinessNetwork;
import tm.card.CEOsFavouriteProject;
import tm.card.CallistoPenalMines;
import tm.card.Capital;
import tm.card.CarbonateProcessing;
import tm.card.CaretakerContract;
import tm.card.Cartel;
import tm.card.CloudSeeding;
import tm.card.ColonizerTrainingCamp;
import tm.card.Comet;
import tm.card.CommercialDistrict;
import tm.card.ConvoyFromEuropa;
import tm.card.CupolaCity;
import tm.card.Decomposers;
import tm.card.DeepWellHeating;
import tm.card.DeimosDown;
import tm.card.DesignedMicroorganisms;
import tm.card.DevelopmentCenter;
import tm.card.DomedCrater;
import tm.card.DustSeals;
import tm.card.EarthCatapult;
import tm.card.EarthOffice;
import tm.card.EcologicalZone;
import tm.card.ElectroCatapult;
import tm.card.EosChasmaNationalPark;
import tm.card.EquatorialMagnetizer;
import tm.card.ExtremeColdFungus;
import tm.card.Farming;
import tm.card.Fish;
import tm.card.FoodFactory;
import tm.card.FueledGenerators;
import tm.card.FusionPower;
import tm.card.GHGFactories;
import tm.card.GHGProducingBacteria;
import tm.card.GanymedeColony;
import tm.card.GeneRepair;
import tm.card.GeothermalPower;
import tm.card.GiantIceAsteroid;
import tm.card.GiantSpaceMirror;
import tm.card.Grass;
import tm.card.GreatDam;
import tm.card.GreatEscarpmentConsortium;
import tm.card.Greenhouses;
import tm.card.Hackers;
import tm.card.Heather;
import tm.card.Herbivores;
import tm.card.HiredRaiders;
import tm.card.IceAsteroid;
import tm.card.ImportedGHG;
import tm.card.ImportedHydrogen;
import tm.card.ImportedNitrogen;
import tm.card.IndustrialCenter;
import tm.card.IndustrialMicrobes;
import tm.card.Insects;
import tm.card.Insulation;
import tm.card.InterstellarColonyShip;
import tm.card.InventorsGuild;
import tm.card.InvestmentLoan;
import tm.card.IoMiningIndustries;
import tm.card.Ironworks;
import tm.card.KelpFarming;
import tm.card.LakeMarineris;
import tm.card.LandClaim;
import tm.card.LargeConvoy;
import tm.card.LavaFlows;
import tm.card.Lichen;
import tm.card.LightningHarvest;
import tm.card.LunarBeam;
import tm.card.Mangrove;
import tm.card.MarsUniversity;
import tm.card.MartianRail;
import tm.card.MassConverter;
import tm.card.MediaArchives;
import tm.card.MediaGroup;
import tm.card.MethaneFromTitan;
import tm.card.Mine;
import tm.card.MineralDeposit;
import tm.card.MiningArea;
import tm.card.MiningExpedition;
import tm.card.MiningRights;
import tm.card.MirandaResort;
import tm.card.MoholeArea;
import tm.card.Moss;
import tm.card.NaturalPreserve;
import tm.card.NitriteReducingBacteria;
import tm.card.NitrogenRichAsteroid;
import tm.card.NitrophilicMoss;
import tm.card.NoctisCity;
import tm.card.NuclearPower;
import tm.card.NuclearZone;
import tm.card.OpenCity;
import tm.card.OptimalAerobraking;
import tm.card.OreProcessor;
import tm.card.PeroxidePower;
import tm.card.PhobosSpaceHaven;
import tm.card.PhysicsComplex;
import tm.card.PowerGrid;
import tm.card.PowerPlant;
import tm.card.PowerSupplyConsortium;
import tm.card.Predators;
import tm.card.QuantumExtractor;
import tm.card.RegolithEaters;
import tm.card.ReleaseOfInertGases;
import tm.card.Research;
import tm.card.ResearchOutpost;
import tm.card.RoboticWorkforce;
import tm.card.RoverConstruction;
import tm.card.Sabotage;
import tm.card.SearchForLife;
import tm.card.SecurityFleet;
import tm.card.SmallAnimals;
import tm.card.SolarPower;
import tm.card.SolarWindPower;
import tm.card.SpaceElevator;
import tm.card.SpaceMirrors;
import tm.card.SpaceStation;
import tm.card.Sponsors;
import tm.card.StandardTechnology;
import tm.card.Steelworks;
import tm.card.StripMine;
import tm.card.SubterraneanReservoir;
import tm.card.SymbioticFungus;
import tm.card.Tardigrades;
import tm.card.TectonicStressPower;
import tm.card.TitaniumMine;
import tm.card.TollStation;
import tm.card.TowingComet;
import tm.card.TransNeptuneProbe;
import tm.card.Trees;
import tm.card.TropicalResort;
import tm.card.UndergroundCity;
import tm.card.UrbanizedArea;
import tm.card.VestaShipyard;
import tm.card.ViralEnhancers;
import tm.card.Virus;
import tm.card.WaterImportFromEuropa;
import tm.card.WavePower;
import tm.card.Worms;
import tm.card.Zeppelins;

// Game deck is built from this class with reflection. There's a single instance of each card
// through this class.
public abstract class Cards {
    public static final Card COLONIZER_TRAINING_CAMP = new ColonizerTrainingCamp();
    public static final Card ASTEROID_MINING_CONSORTIUM = new AsteroidMiningConsortium();
    public static final Card DEEP_WELL_HEATING = new DeepWellHeating();
    public static final Card CLOUD_SEEDING = new CloudSeeding();
    public static final Card SEARCH_FOR_LIFE = new SearchForLife();
    public static final Card INVENTORS_GUILD = new InventorsGuild();
    public static final Card MARTIAL_RAIL = new MartianRail();
    public static final Card CAPITAL = new Capital();
    public static final Card ASTEROID = new Asteroid();
    public static final Card COMET = new Comet();
    public static final Card BIG_ASTEROID = new BigAsteroid();
    public static final Card WATER_IMPORT_FROM_EUROPA = new WaterImportFromEuropa();
    public static final Card SPACE_ELEVATOR = new SpaceElevator();
    public static final Card DEVELOPMENT_CENTER = new DevelopmentCenter();
    public static final Card EQUATORIAL_MAGNETIZER = new EquatorialMagnetizer();
    public static final Card DOMED_CRATER = new DomedCrater();
    public static final Card NOCTIS_CITY = new NoctisCity();
    public static final Card METHANE_FROM_TITAN = new MethaneFromTitan();
    public static final Card IMPORTED_HYDROGEN = new ImportedHydrogen();
    public static final Card RESEARCH_OUTPOST = new ResearchOutpost();
    public static final Card PHOBOS_SPACE_HAVEN = new PhobosSpaceHaven();
    public static final Card BLACK_POLAR_DUST = new BlackPolarDust();
    public static final Card ARCTIC_ALGAE = new ArcticAlgae();
    public static final Card PREDATORS = new Predators();
    public static final Card SPACE_STATION = new SpaceStation();
    public static final Card EOS_CHASMA_NATIONAL_PARK = new EosChasmaNationalPark();
    public static final Card INTERSTELLAR_COLONY_SHIP = new InterstellarColonyShip();
    public static final Card SECURITY_FLEET = new SecurityFleet();
    public static final Card CUPOLA_CITY = new CupolaCity();
    public static final Card LUNAR_BEAM = new LunarBeam();
    public static final Card OPTIMAL_AEROBRAKING = new OptimalAerobraking();
    public static final Card UNDERGROUND_CITY = new UndergroundCity();
    public static final Card REGOLITH_EATERS = new RegolithEaters();
    public static final Card GHG_PRODUCING_BACTERIA = new GHGProducingBacteria();
    public static final Card ANTS = new Ants();
    public static final Card RELEASE_OF_INERT_GASES = new ReleaseOfInertGases();
    public static final Card NITROGEN_RICH_ASTEROID = new NitrogenRichAsteroid();
    public static final Card ROVER_CONSTRUCTION = new RoverConstruction();
    public static final Card DEIMOS_DOWN = new DeimosDown();
    public static final Card ASTEROID_MINING = new AsteroidMining();
    public static final Card FOOD_FACTORY = new FoodFactory();
    public static final Card ARCHAEBACTERIA = new Archaebacteria();
    public static final Card CARBONATE_PROCESSING = new CarbonateProcessing();
    public static final Card NATURAL_PRESERVE = new NaturalPreserve();
    public static final Card NUCLEAR_POWER = new NuclearPower();
    public static final Card LIGHTNING_HARVEST = new LightningHarvest();
    public static final Card ALGAE = new Algae();
    public static final Card ADAPTED_LICHEN = new AdaptedLichen();
    public static final Card TARDIGRADES = new Tardigrades();
    public static final Card VIRUS = new Virus();
    public static final Card MIRANDA_RESORT = new MirandaResort();
    public static final Card FISH = new Fish();
    public static final Card LAKE_MARINERIS = new LakeMarineris();
    public static final Card SMALL_ANIMALS = new SmallAnimals();
    public static final Card KELP_FARMING = new KelpFarming();
    public static final Card MINE = new Mine();
    public static final Card VESTA_SHIPYARD = new VestaShipyard();
    public static final Card BEAM_FROM_THORIUM_ASTEROID = new BeamFromThoriumAsteroid();
    public static final Card MANGROVE = new Mangrove();
    public static final Card TREES = new Trees();
    public static final Card GREAT_ESCARPMENT_CONSORTIUM = new GreatEscarpmentConsortium();
    public static final Card MINERAL_DEPOSIT = new MineralDeposit();
    public static final Card MINING_EXPEDITION = new MiningExpedition();
    public static final Card MINING_AREA = new MiningArea();
    public static final Card BUILDING_INDUSTRIES = new BuildingIndustries();
    public static final Card LAND_CLAIM = new LandClaim();
    public static final Card MINING_RIGHTS = new MiningRights();
    public static final Card SPONSORS = new Sponsors();
    public static final Card ELECTRO_CATAPULT = new ElectroCatapult();
    public static final Card EARTH_CATAPULT = new EarthCatapult();
    public static final Card ADVANCED_ALLOYS = new AdvancedAlloys();
    public static final Card BIRDS = new Birds();
    public static final Card MARS_UNIVERSITY = new MarsUniversity();
    public static final Card VIRAL_ENHANCERS = new ViralEnhancers();
    public static final Card TOWING_COMET = new TowingComet();
    public static final Card SPACE_MIRRORS = new SpaceMirrors();
    public static final Card SOLAR_WIND_POWER = new SolarWindPower();
    public static final Card ICE_ASTEROID = new IceAsteroid();
    public static final Card QUANTUM_EXTRACTOR = new QuantumExtractor();
    public static final Card GIANT_ICE_ASTEROID = new GiantIceAsteroid();
    public static final Card GANYMEDE_COLONY = new GanymedeColony();
    public static final Card CALLISTO_PENAL_MINES = new CallistoPenalMines();
    public static final Card GIANT_SPACE_MIRROR = new GiantSpaceMirror();
    public static final Card TRANS_NEPTUNE_PROBE = new TransNeptuneProbe();
    public static final Card COMMERCIAL_DISTRICT = new CommercialDistrict();
    public static final Card ROBOTIC_WORKFORCE = new RoboticWorkforce();
    public static final Card GRASS = new Grass();
    public static final Card HEATHER = new Heather();
    public static final Card PEROXIDE_POWER = new PeroxidePower();
    public static final Card RESEARCH = new Research();
    public static final Card GENE_REPAIR = new GeneRepair();
    public static final Card IO_MINING_INDUSTRIES = new IoMiningIndustries();
    public static final Card MASS_CONVERTER = new MassConverter();
    public static final Card PHYSICS_COMPLEX = new PhysicsComplex();
    public static final Card GREENHOUSES = new Greenhouses();
    public static final Card NUCLEAR_ZONE = new NuclearZone();
    public static final Card TROPICAL_RESORT = new TropicalResort();
    public static final Card TOLL_STATION = new TollStation();
    public static final Card FUELED_GENERATORS = new FueledGenerators();
    public static final Card IRONWORKS = new Ironworks();
    public static final Card POWER_GRID = new PowerGrid();
    public static final Card STEELWORKS = new Steelworks();
    public static final Card ORE_PROCESSOR = new OreProcessor();
    public static final Card EARTH_OFFICE = new EarthOffice();
    public static final Card ACQUIRED_COMPANY = new AcquiredCompany();
    public static final Card MEDIA_ARCHIVES = new MediaArchives();
    public static final Card OPEN_CITY = new OpenCity();
    public static final Card MEDIA_GROUP = new MediaGroup();
    public static final Card BUSINESS_NETWORK = new BusinessNetwork();
    public static final Card BUSINESS_CONTRACTS = new BusinessContracts();
    public static final Card BRIBED_COMMITTEE = new BribedCommittee();
    public static final Card SOLAR_POWER = new SolarPower();
    public static final Card BREATHING_FILTERS = new BreathingFilters();
    public static final Card ARTIFICIAL_PHOTOSYNTHESIS = new ArtificialPhotosynthesis();
    public static final Card ARTIFICIAL_LAKE = new ArtificialLake();
    public static final Card GEOTHERMAL_POWER = new GeothermalPower();
    public static final Card FARMING = new Farming();
    public static final Card DUST_SEALS = new DustSeals();
    public static final Card URBANIZED_AREA = new UrbanizedArea();
    public static final Card SABOTAGE = new Sabotage();
    public static final Card MOSS = new Moss();
    public static final Card INDUSTRIAL_CENTER = new IndustrialCenter();
    public static final Card HIRED_RAIDERS = new HiredRaiders();
    public static final Card HACKERS = new Hackers();
    public static final Card GHG_FACTORIES = new GHGFactories();
    public static final Card SUBTERRANEAN_RESERVOIR = new SubterraneanReservoir();
    public static final Card ECOLOGICAL_ZONE = new EcologicalZone();
    public static final Card ZEPPELINS = new Zeppelins();
    public static final Card WORMS = new Worms();
    public static final Card DECOMPOSERS = new Decomposers();
    public static final Card FUSION_POWER = new FusionPower();
    public static final Card SYMBIOTIC_FUNGUS = new SymbioticFungus();
    public static final Card EXTREME_COLD_FUNGUS = new ExtremeColdFungus();
    public static final Card ADVANCED_ECOYSTEMS = new AdvancedEcosystems();
    public static final Card GREAT_DAM = new GreatDam();
    public static final Card CARTEL = new Cartel();
    public static final Card STRIP_MINE = new StripMine();
    public static final Card WAVE_POWER = new WavePower();
    public static final Card LAVA_FLOWS = new LavaFlows();
    public static final Card POWER_PLANT = new PowerPlant();
    public static final Card MOHOLE_AREA = new MoholeArea();
    public static final Card LARGE_CONVOY = new LargeConvoy();
    public static final Card TITANIUM_MINE = new TitaniumMine();
    public static final Card TECTONIC_STRESS_POWER = new TectonicStressPower();
    public static final Card NITROPHILIC_MOSS = new NitrophilicMoss();
    public static final Card HERBIVORES = new Herbivores();
    public static final Card INSECTS = new Insects();
    public static final Card CEOS_FAVORITE_PROJECT = new CEOsFavouriteProject();
    public static final Card ANTI_GRAVITY_TECHNOLOGY = new AntiGravityTechnology();
    public static final Card INVESTMENT_LOAN = new InvestmentLoan();
    public static final Card INSULATION = new Insulation();
    public static final Card ADAPTATION_TECHNOLOGY = new AdaptationTechnology();
    public static final Card CARETAKER_CONTRACT = new CaretakerContract();
    public static final Card DESIGNED_MICROORGANISMS = new DesignedMicroorganisms();
    public static final Card STANDARD_TECHNOLOGY = new StandardTechnology();
    public static final Card NITRITE_REDUCING_BACTERIA = new NitriteReducingBacteria();
    public static final Card INDUSTRIAL_MICROBES = new IndustrialMicrobes();
    public static final Card LICHEN = new Lichen();
    public static final Card POWER_SUPPLY_CONSORTIUM = new PowerSupplyConsortium();
    public static final Card CONVOY_FROM_EUROPA = new ConvoyFromEuropa();
    public static final Card IMPORTED_GHG = new ImportedGHG();
    public static final Card IMPORTED_NITROGEN = new ImportedNitrogen();

    public static Deque<Card> buildDeck() {
        final Deque<Card> deck = new ArrayDeque<>();
        final Field[] fields = Cards.class.getDeclaredFields();
        for (Field f : fields) {
            try {
                deck.add((Card) f.get(null));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return deck;
    }
}
