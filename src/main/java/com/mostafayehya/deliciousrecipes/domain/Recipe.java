package com.mostafayehya.deliciousrecipes.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private Integer servings;
    private String sources;
    private String url;

    @Lob
    private String directions;
    //todo add
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id") )
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    public void setNotes(Note notes){
        this.note=notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCookTime() {
        return this.cookTime;
    }

    public Integer getPrepTime() {
        return this.prepTime;
    }

    public Integer getServings() {
        return this.servings;
    }

    public String getSources() {
        return this.sources;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDirections() {
        return this.directions;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Byte[] getImage() {
        return this.image;
    }

    public Note getNote() {
        return this.note;
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Recipe)) return false;
        final Recipe other = (Recipe) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$cookTime = this.getCookTime();
        final Object other$cookTime = other.getCookTime();
        if (this$cookTime == null ? other$cookTime != null : !this$cookTime.equals(other$cookTime)) return false;
        final Object this$prepTime = this.getPrepTime();
        final Object other$prepTime = other.getPrepTime();
        if (this$prepTime == null ? other$prepTime != null : !this$prepTime.equals(other$prepTime)) return false;
        final Object this$servings = this.getServings();
        final Object other$servings = other.getServings();
        if (this$servings == null ? other$servings != null : !this$servings.equals(other$servings)) return false;
        final Object this$sources = this.getSources();
        final Object other$sources = other.getSources();
        if (this$sources == null ? other$sources != null : !this$sources.equals(other$sources)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$directions = this.getDirections();
        final Object other$directions = other.getDirections();
        if (this$directions == null ? other$directions != null : !this$directions.equals(other$directions))
            return false;
        final Object this$difficulty = this.getDifficulty();
        final Object other$difficulty = other.getDifficulty();
        if (this$difficulty == null ? other$difficulty != null : !this$difficulty.equals(other$difficulty))
            return false;
        if (!java.util.Arrays.deepEquals(this.getImage(), other.getImage())) return false;
        final Object this$note = this.getNote();
        final Object other$note = other.getNote();
        if (this$note == null ? other$note != null : !this$note.equals(other$note)) return false;
        final Object this$ingredients = this.getIngredients();
        final Object other$ingredients = other.getIngredients();
        if (this$ingredients == null ? other$ingredients != null : !this$ingredients.equals(other$ingredients))
            return false;
        final Object this$categories = this.getCategories();
        final Object other$categories = other.getCategories();
        if (this$categories == null ? other$categories != null : !this$categories.equals(other$categories))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Recipe;
    }


    public String toString() {
        return "Recipe(id=" + this.getId() + ", description=" + this.getDescription() + ", cookTime=" + this.getCookTime() + ", prepTime=" + this.getPrepTime() + ", servings=" + this.getServings() + ", sources=" + this.getSources() + ", url=" + this.getUrl() + ", directions=" + this.getDirections() + ", difficulty=" + this.getDifficulty() + ", image=" + java.util.Arrays.deepToString(this.getImage()) + ", note=" + this.getNote() + ", ingredients=" + this.getIngredients() + ", categories=" + this.getCategories() + ")";
    }
}

