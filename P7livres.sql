PGDMP     '    2                x           utilisateur    12.1    12.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    139115    utilisateur    DATABASE     �   CREATE DATABASE utilisateur WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE utilisateur;
                postgres    false            �            1259    151651     utilisateur_livre_user_role_list    TABLE     �   CREATE TABLE public.utilisateur_livre_user_role_list (
    utilisateur_livre_id bigint NOT NULL,
    user_role_list character varying(255)
);
 4   DROP TABLE public.utilisateur_livre_user_role_list;
       public         heap    postgres    false                      0    151651     utilisateur_livre_user_role_list 
   TABLE DATA           `   COPY public.utilisateur_livre_user_role_list (utilisateur_livre_id, user_role_list) FROM stdin;
    public          postgres    false    204   �       �
           2606    151654 <   utilisateur_livre_user_role_list fk8xov8ahjutr4jmk36rykl0drl    FK CONSTRAINT     �   ALTER TABLE ONLY public.utilisateur_livre_user_role_list
    ADD CONSTRAINT fk8xov8ahjutr4jmk36rykl0drl FOREIGN KEY (utilisateur_livre_id) REFERENCES public.utilisateur_livre(id);
 f   ALTER TABLE ONLY public.utilisateur_livre_user_role_list DROP CONSTRAINT fk8xov8ahjutr4jmk36rykl0drl;
       public          postgres    false    204                  x�3�v�2�tt����`n� P6^     