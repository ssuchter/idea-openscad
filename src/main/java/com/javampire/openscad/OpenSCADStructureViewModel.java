package com.javampire.openscad;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import com.javampire.openscad.psi.OpenSCADFile;
import com.javampire.openscad.psi.impl.OpenSCADFunctionDeclarationImpl;
import com.javampire.openscad.psi.impl.OpenSCADModuleDeclarationImpl;
import org.fest.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class OpenSCADStructureViewModel
        extends StructureViewModelBase
        implements StructureViewModel.ElementInfoProvider
{
    public OpenSCADStructureViewModel(PsiFile psiFile) {
        super(psiFile, new OpenSCADStructureViewElement(psiFile));
        // TODO: see why this doesn't work:
        withSuitableClasses(
                OpenSCADModuleDeclarationImpl.class,
                OpenSCADFunctionDeclarationImpl.class
        );
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof OpenSCADFile;
    }

    @NotNull
    @Override
    public Grouper[] getGroupers() {
        return Arrays.array(new OpenSCADElementGrouper());
    }
}