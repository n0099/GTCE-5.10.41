package gregtech.common;

import gregtech.common.tools.GT_Tool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public class GT_DummyWorld extends World {

    public static WorldSettings DUMMY_SETTINGS =
            new WorldSettings(1L, GameType.SURVIVAL, true, false, WorldType.field_77137_b);

    public static WorldInfo DUMMY_INFO = new WorldInfo(DUMMY_SETTINGS, "DUMMY_DIMENSION");

    //public GT_IteratorRandom mRandom = new GT_IteratorRandom();
    public ItemStack mLastSetBlock = null;

    public GT_DummyWorld(ISaveHandler par1iSaveHandler, String par2Str, WorldSettings par4WorldSettings, Profiler par5Profiler) {
        super(par1iSaveHandler, par1iSaveHandler.func_75757_d(), new WorldProviderSurface(), par5Profiler, false);
        //this.rand = this.mRandom;
    }

    public GT_DummyWorld() {
        this(new ISaveHandler() {

                 @Override
                 public WorldInfo func_75757_d() {
                     return DUMMY_INFO;
                 }

                 @Override
                 public void func_75762_c() throws MinecraftException {}

                 @Override
                 public IChunkLoader func_75763_a(WorldProvider provider) {
                     return new IChunkLoader() {
                         @Nullable
                         @Override
                         public Chunk func_75815_a(World worldIn, int x, int z) throws IOException {
                             return null;
                         }

                         @Override
                         public void func_75816_a(World worldIn, Chunk chunkIn) throws MinecraftException, IOException {

                         }

                         @Override
                         public void func_75819_b(World worldIn, Chunk chunkIn) throws IOException {

                         }

                         @Override
                         public void func_75817_a() {

                         }

                         @Override
                         public void func_75818_b() {

                         }
                     };
                 }

                 @Override
                 public void func_75755_a(WorldInfo worldInformation, NBTTagCompound tagCompound) {}

                 @Override
                 public void func_75761_a(WorldInfo worldInformation) {}

                 @Override
                 public IPlayerFileData func_75756_e() {
                     return new IPlayerFileData() {
                         @Override
                         public void func_75753_a(EntityPlayer player) {}

                         @Override
                         public NBTTagCompound func_75752_b(EntityPlayer player) {
                             return new NBTTagCompound();
                         }

                         @Override
                         public String[] func_75754_f() {
                             return new String[0];
                         }
                     };
                 }

                 @Override
                 public void func_75759_a() {}

                 @Override
                 public File func_75765_b() {
                     return null;
                 }

                 @Override
                 public File func_75758_b(String mapName) {
                     return null;
                 }

                 @Override
                 public TemplateManager func_186340_h() {
                     return null;
                 }
             }, DUMMY_INFO.func_76065_j(), DUMMY_SETTINGS, new Profiler());
    }

    protected IChunkProvider func_72970_h() {
        return new IChunkProvider() {
            @Nullable
            @Override
            public Chunk func_186026_b(int x, int z) {
                return null;
            }

            @Override
            public Chunk func_186025_d(int x, int z) {
                return null;
            }

            @Override
            public boolean func_73156_b() {
                return false;
            }

            @Override
            public String func_73148_d() {
                return "Dummy";
            }
        };
    }

    @Override
    protected boolean func_175680_a(int x, int z, boolean allowEmpty) {
        return false;
    }

    @Override
    public Entity func_73045_a(int aEntityID) {
        return null;
    }

    @Override
    public boolean func_180501_a(BlockPos pos, IBlockState newState, int flags) {
        this.mLastSetBlock = GT_Tool.getBlockStack(newState);
        return true;
    }

    @Override
    public float func_72971_b(float par1) {
        return 1.0F;
    }

    @Override
    public Biome getBiomeForCoordsBody(BlockPos pos) {
        return func_180494_b(pos);
    }

    @Override
    public Biome func_180494_b(BlockPos pos) {
        return Biomes.field_76772_c;
    }

    @Override
    public int func_175699_k(BlockPos pos) {
        return 10;
    }

    @Override
    public int func_175626_b(BlockPos pos, int lightValue) {
        return 10;
    }

    @Override
    public IBlockState func_180495_p(BlockPos pos) {
        return Blocks.field_150350_a.func_176223_P();
    }

    @Nullable
    @Override
    public TileEntity func_175625_s(BlockPos pos) {
        return null;
    }

    @Override
    public void func_175690_a(BlockPos pos, @Nullable TileEntity tileEntityIn) {}

    @Override
    public void func_175713_t(BlockPos pos) {}

    @Override
    public boolean func_175700_a(TileEntity tile) {
        return true;
    }

    @Override
    public boolean func_175710_j(BlockPos pos) {
        return true;
    }


}
